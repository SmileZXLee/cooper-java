package cn.zxlee.cooper.core.wx.business.decrypt.entity;

import cn.zxlee.cooper.base.entity.BaseResult;

import java.util.Map;

/**
 * @program: cooper
 * @description: 微信信息解密结果类
 * @author: zxlee
 * @create: 2022-07-17 18:50
 **/
public class WxDecryptResult extends BaseResult {
    private Map<String, Object> result;

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }
}
