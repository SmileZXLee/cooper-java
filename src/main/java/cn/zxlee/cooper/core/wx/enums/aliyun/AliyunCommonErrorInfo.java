package cn.zxlee.cooper.core.wx.enums.aliyun;

public enum AliyunCommonErrorInfo {
    SEND_SMS_FAILED(-3, "aliyun短信发送失败");

    private final long code;
    private final String msg;

    AliyunCommonErrorInfo(long code, String msg) {
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
