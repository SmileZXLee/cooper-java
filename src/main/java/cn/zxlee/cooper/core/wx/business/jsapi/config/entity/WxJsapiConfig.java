package cn.zxlee.cooper.core.wx.business.jsapi.config.entity;

/**
 * @program: cooper
 * @description:
 * @author: zxlee
 * @create: 2022-07-17 22:40
 **/
public class WxJsapiConfig {
    private String appid;
    private String noncestr;
    private String url;
    private Long timestamp;
    private String signature;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
