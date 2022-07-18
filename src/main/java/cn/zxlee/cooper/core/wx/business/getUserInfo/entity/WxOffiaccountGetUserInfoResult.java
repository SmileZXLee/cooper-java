package cn.zxlee.cooper.core.wx.business.getUserInfo.entity;

import cn.zxlee.cooper.base.entity.BaseResult;

/**
 * @program: cooper
 * @description: 微信公众号获取用户信息结果包装类
 * @author: zxlee
 * @create: 2022-07-18 11:57
 **/

public class WxOffiaccountGetUserInfoResult extends BaseResult {
    private WxOffiaccountUserInfo userInfo;

    public WxOffiaccountUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(WxOffiaccountUserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
