package cn.zxlee.cooper.core.wx.business.login.entity;

import cn.zxlee.cooper.base.entity.BaseResult;

/**
 * @program: cooper
 * @description:
 * @author: zxlee
 * @create: 2022-07-16 18:11
 **/
public class WxMpLoginResult extends BaseResult {
    private String openid;
    private String sessionKey;
    private String unionid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}