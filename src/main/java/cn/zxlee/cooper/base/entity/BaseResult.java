package cn.zxlee.cooper.base.entity;

import java.util.Map;

/**
 * @program: cooper
 * @description:
 * @author: zxlee
 * @create: 2022-07-16 17:03
 **/
public class BaseResult {
    private long code;
    private Map rawData;
    private String errMsg;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public Map getRawData() {
        return rawData;
    }

    public void setRawData(Map rawData) {
        this.rawData = rawData;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
