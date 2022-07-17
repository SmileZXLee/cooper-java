package cn.zxlee.cooper.core.wx.business.jsapi.config;

import cn.zxlee.cooper.config.wx.WxOffiaccountProperties;
import cn.zxlee.cooper.core.wx.business.jsapi.config.entity.WxJsapiConfig;
import cn.zxlee.cooper.core.wx.business.jsapi.config.entity.WxJsapiConfigResult;
import cn.zxlee.cooper.core.wx.business.jsapiTicket.WxJsapiTicketGenerator;
import cn.zxlee.cooper.core.wx.business.jsapiTicket.entity.WxJsapiTicketResult;
import cn.zxlee.cooper.utils.EncryptionUtils;
import cn.zxlee.cooper.utils.RandomUtils;
import cn.zxlee.cooper.utils.jsonConverter.IJsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: cooper
 * @description:
 * @author: zxlee
 * @create: 2022-07-17 20:40
 **/

@Component
public class WxJsapiConfigGenerator {
    @Resource
    private WxOffiaccountProperties config;

    @Autowired
    private WxJsapiTicketGenerator jsapiTicketGenerator;

    @Autowired
    private IJsonConverter jsonConverter;

    public WxJsapiConfigResult getConfig(String url) {
        WxJsapiConfigResult result = new WxJsapiConfigResult();
        WxJsapiTicketResult ticketResult = jsapiTicketGenerator.getTicket();
        if (ticketResult.getCode() == 0) {
            String noncestr = RandomUtils.getRandomStr(16);
            long timestamp = System.currentTimeMillis() / 1000;
            String jsapiTicket = ticketResult.getTicket();

            Map<String, Object> configMap = new HashMap<>();
            configMap.put("noncestr", noncestr);
            configMap.put("timestamp", timestamp);
            configMap.put("jsapi_ticket", jsapiTicket);
            configMap.put("url", url);

            String orderedStr = EncryptionUtils.map2OrderedStr(configMap);
            String sign = EncryptionUtils.sha1(orderedStr);
            configMap.put("signature", sign);
            configMap.put("appid", config.getAppid());

            result.setCode(0);
            result.setConfig(jsonConverter.map2Obj(configMap,WxJsapiConfig.class));
            result.setRawData(configMap);
        } else {
            result.setCode(ticketResult.getCode());
            result.setErrMsg("获取jsapiConfig失败，因为ticket获取失败，原因是：" + ticketResult.getErrMsg());
        }
        return result;

    }



    private String generateNoncestr() {
        return RandomUtils.getRandomStr(16);
    }
}
