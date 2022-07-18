package cn.zxlee.cooper.core.wx.business.accessToken.entity;

import cn.zxlee.cooper.base.entity.BaseResult;

/**
 * @program: cooper
 * @description: accessToken结果包装类
 * @author: zxlee
 * @create: 2022-07-16 17:00
 **/

public class WxAccessTokenResult extends BaseResult {
    /**
     * accessToken
     */
    private String accessToken;

    /**
     * accessToken多少秒后过期
     */
    private long expiresIn;

    /**
     * accessToken创建时间
     */
    private long createTime;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expireIn) {
        this.expiresIn = expireIn;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
