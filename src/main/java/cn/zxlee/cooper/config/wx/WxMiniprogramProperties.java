package cn.zxlee.cooper.config.wx;

import cn.zxlee.cooper.config.wx.base.AbstractWxMpProperties;
import cn.zxlee.cooper.constants.CooperConstants;
import cn.zxlee.cooper.exception.CooperRequireInfoException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @program: cooper
 * @description: 微信小程序配置
 * @author: zxlee
 * @create: 2022-07-16 20:23
 **/

@Component
@ConfigurationProperties(prefix = "cooper.wx.miniprogram")
public class WxMiniprogramProperties extends AbstractWxMpProperties {
    /**
     * 微信小程序登录url(选填)
     */
    private String loginUrl;

    /**
     * 微信小程序支付url(选填)
     */
    private String payUrl;

    @Override
    public String getAppid() {
        if (!StringUtils.hasText(appid)) {
            throw new CooperRequireInfoException("微信小程序appid未配置");
        }
        return appid;
    }

    @Override
    public String getSecret() {
        if (!StringUtils.hasText(secret)) {
            throw new CooperRequireInfoException("微信小程序secret未配置");
        }
        return secret;
    }

    @Override
    public String getMchid() {
        if (!StringUtils.hasText(mchid)) {
            throw new CooperRequireInfoException("微信小程序直连商户号(mchid)未配置");
        }
        return mchid;
    }

    @Override
    public String getPayNotifyUrl() {
        if (!StringUtils.hasText(payNotifyUrl)) {
            throw new CooperRequireInfoException("微信小程序异步接收微信支付结果通知的回调地址未配置");
        }
        return payNotifyUrl;
    }

    public String getLoginUrl() {
        if (!StringUtils.hasText(loginUrl)) {
            return CooperConstants.WX_MINIPROGRAM_LOGIN_URL;
        }
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getPayUrl() {
        if (!StringUtils.hasText(payUrl)) {
            return CooperConstants.WX_MINIPROGRAM_PAY_URL;
        }
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }
}
