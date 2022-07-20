package cn.zxlee.cooper.core.aliyun.business.sms.entity;

import cn.zxlee.cooper.base.entity.BaseResult;

/**
 * @program: cooper
 * @description:
 * @author: zxlee
 * @create: 2022-07-19 21:56
 **/
public class AliyunSendSmsResult extends BaseResult {
    private String checkCode;

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }
}
