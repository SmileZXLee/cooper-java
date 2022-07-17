package cn.zxlee.cooper.core.wx.enums;

public enum WxAccessTokenTarget {
    MINIPROGRAM("MINIPROGRAM"),
    OFFIACCOUNT("OFFIACCOUNT");

    private final String target;

    WxAccessTokenTarget(String target) {
        this.target = target;
    }

    public String getTarget() {
        return target;
    }
}
