package cn.zxlee.cooper.config.wx.base;


import cn.zxlee.cooper.constants.CooperConstants;
import cn.zxlee.cooper.exception.CooperRequireInfoException;
import org.springframework.util.StringUtils;

/**
 * @program: cooper
 * @description: AbstractWxMpProperties
 * @author: zxlee
 * @create: 2022-07-17 23:32
 **/
public abstract class AbstractWxMpProperties {
    /**
     * appid
     */
    protected String appid;

    /**
     * secret
     */
    protected String secret;

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
}
