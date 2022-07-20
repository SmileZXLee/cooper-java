package cn.zxlee.cooper.config.wx;

import cn.zxlee.cooper.config.wx.base.AbstractWxMpProperties;
import cn.zxlee.cooper.constants.CooperConstants;
import cn.zxlee.cooper.exception.CooperRequireInfoException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @program: cooper
 * @description: 微信公众号配置
 * @author: zxlee
 * @create: 2022-07-17 23:19
 **/

@Component
@ConfigurationProperties(prefix = "cooper.wx.offiaccount")
public class WxOffiaccountProperties extends AbstractWxMpProperties {
    /**
     * 微信公众号登录url(选填)
     */
    private String loginUrl;

    /**
     * 微信公众号获取用户信息url(选填)
     */
    private String getUserInfoUrl;

    /**
     * 微信公众号获取ticket url(选填)
     */
    private String getTicketUrl;

    @Override
    public String getAppid() {
        if (!StringUtils.hasText(appid)) {
            throw new CooperRequireInfoException("微信公众号appid未配置");
        }
        return appid;
    }

    @Override
    public String getSecret() {
        if (!StringUtils.hasText(secret)) {
            throw new CooperRequireInfoException("微信公众号secret未配置");
        }
        return secret;
    }

    @Override
    public String getMchid() {
        if (!StringUtils.hasText(mchid)) {
            throw new CooperRequireInfoException("微信公众号直连商户号(mchid)未配置");
        }
        return mchid;
    }

    @Override
    public String getPayNotifyUrl() {
        if (!StringUtils.hasText(payNotifyUrl)) {
            throw new CooperRequireInfoException("微信公众号异步接收微信支付结果通知的回调地址未配置");
        }
        return payNotifyUrl;
    }

    public String getLoginUrl() {
        if (!StringUtils.hasText(loginUrl)) {
            return CooperConstants.WX_OFFIACCOUNT_LOGIN_URL;
        }
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getGetUserInfoUrl() {
        if (!StringUtils.hasText(getUserInfoUrl)) {
            return CooperConstants.WX_OFFIACCOUNT_GET_USERINFO_URL;
        }
        return getUserInfoUrl;
    }

    public void setGetUserInfoUrl(String getUserInfoUrl) {
        this.getUserInfoUrl = getUserInfoUrl;
    }

    public String getGetTicketUrl() {
        if (!StringUtils.hasText(getTicketUrl)) {
            return CooperConstants.WX_OFFIACCOUNT_GET_TICKET_URL;
        }
        return getTicketUrl;
    }

    public void setGetTicketUrl(String getTicketUrl) {
        this.getTicketUrl = getTicketUrl;
    }

}
