package cn.zxlee.cooper.config.wx.base;

import cn.zxlee.cooper.exception.CooperRequireInfoException;
import org.springframework.util.StringUtils;

/**
 * @program: cooper
 * @description:
 * @author: zxlee
 * @create: 2022-07-17 23:32
 **/
public abstract class WxMpProperties {
    /**
     * appid
     */
    protected String appid;
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
