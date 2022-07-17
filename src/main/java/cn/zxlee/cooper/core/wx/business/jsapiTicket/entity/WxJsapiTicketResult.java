package cn.zxlee.cooper.core.wx.business.jsapiTicket.entity;

import cn.zxlee.cooper.base.entity.BaseResult;

/**
 * @program: cooper
 * @description:
 * @author: zxlee
 * @create: 2022-07-17 19:36
 **/

public class WxJsapiTicketResult extends BaseResult {
    /**
     * ticket
     */
    private String ticket;

    /**
     * ticket多少秒后过期
     */
    private long expiresIn;

    /**
     * ticket创建时间
     */
    private long createTime;

    public String getTicket() { return ticket; }

    public void setTicket(String ticket) {
        this.ticket = ticket;
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
