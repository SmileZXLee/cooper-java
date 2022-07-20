package cn.zxlee.cooper.core.wx.business.pay.base;

import cn.zxlee.cooper.core.wx.business.pay.entity.WxMiniprogramPayParam;

/**
 * @program: cooper
 * @description: 抽象的微信小程序或公众号支付类
 * @author: zxlee
 * @create: 2022-07-18 14:14
 **/
public abstract class AbstractWxMpPay {
    public abstract Object prepay(WxMiniprogramPayParam param);
}
