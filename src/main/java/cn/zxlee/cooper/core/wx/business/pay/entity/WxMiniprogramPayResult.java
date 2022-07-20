package cn.zxlee.cooper.core.wx.business.pay.entity;

import cn.zxlee.cooper.base.entity.BaseResult;

/**
 * @program: cooper
 * @description:
 * @author: zxlee
 * @create: 2022-07-18 14:16
 **/
public class WxMiniprogramPayResult extends BaseResult {
    private String prepayId;

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }
}
