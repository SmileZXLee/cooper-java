package cn.zxlee.cooper.config.wx.base;


import cn.zxlee.cooper.constants.CooperConstants;
import org.springframework.util.StringUtils;

/**
 * @program: cooper
 * @description: AbstractWxMpProperties
 * @author: zxlee
 * @create: 2022-07-17 23:32
 **/
public abstract class AbstractWxMpProperties {
    /**
     * 微信appid
     */
    protected String appid;

    /**
     * 微信appsecret
     */
    protected String secret;

    /**
     * 直连商户号Id
     */
    protected String mchid;

    /**
     * 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。 公网域名必须为https，如果是走专线接入，使用专线NAT IP或者私有回调域名可使用http
     */
    protected String payNotifyUrl;

    /**
     * 微信accessToken生成url
     */
    protected String accessTokenUrl;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getAccessTokenUrl() {
        if (!StringUtils.hasText(accessTokenUrl)) {
            return CooperConstants.WX_ACCESSTOKEN_URL;
        }
        return accessTokenUrl;
    }

    public void setAccessTokenUrl(String accessTokenUrl) {
        this.accessTokenUrl = accessTokenUrl;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getPayNotifyUrl() {
        return payNotifyUrl;
    }

    public void setPayNotifyUrl(String payNotifyUrl) {
        this.payNotifyUrl = payNotifyUrl;
    }
}
