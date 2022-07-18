package cn.zxlee.cooper.config.wx.base;


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
}
