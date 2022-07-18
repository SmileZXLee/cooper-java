package cn.zxlee.cooper.core.wx.business.jsapiTicket;

import cn.zxlee.cooper.config.wx.WxOffiaccountProperties;
import cn.zxlee.cooper.core.wx.business.accessToken.WxAccessTokenGenerator;
import cn.zxlee.cooper.core.wx.business.accessToken.entity.WxAccessTokenResult;
import cn.zxlee.cooper.core.wx.business.jsapiTicket.entity.WxJsapiTicketResult;
import cn.zxlee.cooper.core.wx.enums.WxAccessTokenTarget;
import cn.zxlee.cooper.utils.HttpReqUtils;
import cn.zxlee.cooper.utils.jsonConverter.IJsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: cooper
 * @description: jsapi ticket生成类
 * @author: zxlee
 * @create: 2022-07-17 19:52
 **/

@Component
public class WxJsapiTicketGenerator {
    @Resource
    private WxOffiaccountProperties config;

    @Autowired
    private IJsonConverter jsonConverter;

    @Autowired
    private WxAccessTokenGenerator accessTokenGenerator;

    private WxJsapiTicketResult ticket;

    public WxJsapiTicketResult getTicket() {
        if (ticket != null && ticket.getCode() == 0) {
            synchronized (WxJsapiTicketResult.class) {
                if (ticket != null && ticket.getCode() == 0) {
                    long currentTimeMillis = System.currentTimeMillis();
                    long expireIn = ticket.getExpiresIn();
                    long createTime = ticket.getCreateTime();
                    if (createTime + expireIn * 1000 < currentTimeMillis) {
                        // ticket过期
                        ticket = createTicket();
                    }
                } else {
                    // 无可用的ticket
                    ticket = createTicket();
                }
            }
        } else {
            // 无可用的ticket
            ticket = createTicket();
        }
        return ticket;
    }

    private WxJsapiTicketResult createTicket() {
        WxJsapiTicketResult ticketResult = new WxJsapiTicketResult();
        Map<String, String> getMap = new HashMap<>();
        WxAccessTokenResult accessTokenResult = accessTokenGenerator.getAccessToken(WxAccessTokenTarget.OFFIACCOUNT);
        if (accessTokenResult.getCode() == 0) {
            getMap.put("type", "jsapi");
            getMap.put("access_token", accessTokenResult.getAccessToken());
            String result = HttpReqUtils.doGet(config.getGetTicketUrl(), getMap);
            Map<String, Object> innerMap = jsonConverter.str2Map(result);
            ticketResult.setRawData(innerMap);
            if (innerMap.containsKey("ticket")) {
                ticketResult.setCode(0);
                ticketResult.setTicket((String)innerMap.get("ticket"));
                ticketResult.setExpiresIn(((Integer)innerMap.get("expires_in")).longValue());
                ticketResult.setCreateTime(System.currentTimeMillis());
            } else {
                ticketResult.setCode(((Integer)innerMap.get("errcode")).longValue());
                ticketResult.setErrMsg((String)innerMap.get("errmsg"));
            }
        } else {
            ticketResult.setRawData(accessTokenResult.getRawData());
            ticketResult.setCode(accessTokenResult.getCode());
            ticketResult.setErrMsg("ticket生成失败，由于accessToken生成失败，原因为：" + accessTokenResult.getErrMsg());
        }
        return ticketResult;
    }

}
