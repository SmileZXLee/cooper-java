package cn.zxlee.cooper.constants;

import cn.zxlee.cooper.core.wx.business.login.WxMiniprogramLogin;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: cooper
 * @description: 常量类
 * @author: zxlee
 * @create: 2022-07-18 12:34
 **/
public final class CooperConstants {
    private CooperConstants() {};

    /**
     * 微信accessToken生成url
     */
    public static final String WX_ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

    /**
     * 微信小程序登录url
     */
    public static final String WX_MINIPROGRAM_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";

    /**
     * 微信公众号登录url
     */
    public static final String WX_OFFIACCOUNT_LOGIN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";

    /**
     * 微信公众号获取用户信息url
     */
    public static final String WX_OFFIACCOUNT_GET_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo";

    /**
     * 微信公众号获取ticket url
     */
    public static final String WX_OFFIACCOUNT_GET_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";

}
