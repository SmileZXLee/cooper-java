package cn.zxlee.cooper.core.wx.business.pay;

import cn.zxlee.cooper.config.wx.WxMiniprogramProperties;
import cn.zxlee.cooper.core.wx.business.pay.base.AbstractWxMpPay;
import cn.zxlee.cooper.core.wx.business.pay.entity.WxMiniprogramPayParam;
import cn.zxlee.cooper.core.wx.business.pay.entity.WxMiniprogramPayResult;
import cn.zxlee.cooper.core.wx.business.pay.entity.WxMiniprogramPrePayResult;
import cn.zxlee.cooper.utils.EncryptionUtils;
import cn.zxlee.cooper.utils.HttpReqUtils;
import cn.zxlee.cooper.utils.RandomUtils;
import cn.zxlee.cooper.utils.jsonConverter.IJsonConverter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: cooper
 * @description: 微信小程序支付
 * @author: zxlee
 * @create: 2022-07-18 14:15
 **/
public class WxMiniprogramPay extends AbstractWxMpPay {

    @Resource
    private WxMiniprogramProperties config;

    @Autowired
    private IJsonConverter jsonConverter;

    @Override
    public WxMiniprogramPrePayResult prepay(WxMiniprogramPayParam param) {
        WxMiniprogramPrePayResult result = new WxMiniprogramPrePayResult();

        // JSAPI下单
        Map<String, Object> getMap = jsonConverter.obj2Map(param);
        String resultStr = HttpReqUtils.doPostJson(config.getPayUrl(), jsonConverter.obj2Str(getMap));
        Map<String, Object> innerMap = jsonConverter.str2Map(resultStr);

        result.setRawData(innerMap);
        if (null == innerMap.get("errcode") || 0 == (int)innerMap.get("errcode")) {
            // 微信小程序JSAPI下单成功
            result.setCode(0);
            result.setPrepayId((String)innerMap.get("prepay_id"));
        } else {
            // 微信小程序JSAPI下单失败
            result.setCode(((Integer)innerMap.get("errcode")).longValue());
            result.setErrMsg((String)innerMap.get("errmsg"));
        }
        return result;
    }

    public WxMiniprogramPayResult pay(WxMiniprogramPayParam param){
        param.setAppid(config.getAppid());
        param.setMchid(config.getMchid());
        param.setNotifyUrl(config.getPayNotifyUrl());

        WxMiniprogramPayResult result = new WxMiniprogramPayResult();
        WxMiniprogramPrePayResult prePayResult = prepay(param);
        if (0 == prePayResult.getCode()) {
            String nonceStr = RandomUtils.getRandomStr(16);
            long timeStamp = System.currentTimeMillis() / 1000;

            Map<String, Object> configMap = new HashMap<>();
            configMap.put("appId", param.getAppid());
            configMap.put("nonceStr", nonceStr);
            configMap.put("timeStamp", timeStamp);
            configMap.put("signType", "RSA");
            configMap.put("package", "prepay_id=" + prePayResult.getPrepayId());

            String orderedStr = EncryptionUtils.map2OrderedStr(configMap);
            String sign = EncryptionUtils.sha1(orderedStr);
            configMap.put("paySign", sign);
        } else {
            result.setCode(prePayResult.getCode());
            result.setErrMsg("微信小程序支付失败，因为JSAPI预下单失败，失败原因为：" + prePayResult.getErrMsg());
        }

        return result;
    }

}
