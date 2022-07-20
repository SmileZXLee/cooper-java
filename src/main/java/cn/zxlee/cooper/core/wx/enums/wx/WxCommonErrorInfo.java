package cn.zxlee.cooper.core.wx.enums.wx;

public enum WxCommonErrorInfo {
    DECRYPT_FAILED(-1, "微信信息解密失败"),
    TICKET_GET_FAILED(-2, "ticket生成失败");


    private final long code;
    private final String msg;

    WxCommonErrorInfo(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public long getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
