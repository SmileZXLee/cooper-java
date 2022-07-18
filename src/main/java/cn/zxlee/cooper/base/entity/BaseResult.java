package cn.zxlee.cooper.base.entity;

import java.util.Map;

/**
 * @program: cooper
 * @description: 基础的Result包装类
 * @author: zxlee
 * @create: 2022-07-16 17:03
 **/
public class BaseResult {
    /**
     * 0代表成功，非0代表异常
     */
    private long code;

    /**
     * 原始的未经处理的数据
     */
    private Map rawData;

    /**
     * 错误信息
     */
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
