package cn.zxlee.cooper.core.wx.business.login.base;

import cn.zxlee.cooper.base.entity.BaseResult;

/**
 * @program: cooper
 * @description: 微信小程序或公众号登录
 * @author: zxlee
 * @create: 2022-07-18 11:08
 **/
public abstract class AbstractWxMpLogin {
    public abstract BaseResult login(String code);
}
