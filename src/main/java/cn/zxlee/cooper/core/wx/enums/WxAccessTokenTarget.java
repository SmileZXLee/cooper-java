package cn.zxlee.cooper.core.wx.enums;

/**
 * @program: cooper
 * @description: 微信accessToken生成目标枚举类
 * @author: zxlee
 * @create: 2022-07-17 23:12
 **/
public enum WxAccessTokenTarget {
    /**
     * 生成微信小程序的accessToken
     */
    MINIPROGRAM("MINIPROGRAM"),

    /**
     * 生成微信公众号的accessToken
     */
    OFFIACCOUNT("OFFIACCOUNT");

    private final String target;

    WxAccessTokenTarget(String target) {
        this.target = target;
    }

    public String getTarget() {
        return target;
    }
}
