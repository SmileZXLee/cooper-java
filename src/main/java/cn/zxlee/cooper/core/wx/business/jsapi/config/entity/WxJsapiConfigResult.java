package cn.zxlee.cooper.core.wx.business.jsapi.config.entity;

import cn.zxlee.cooper.base.entity.BaseResult;

/**
 * @program: cooper
 * @description: jsapi配置结果包装类
 * @author: zxlee
 * @create: 2022-07-17 21:31
 **/
public class WxJsapiConfigResult extends BaseResult {
    private WxJsapiConfig config;

    public WxJsapiConfig getConfig() {
        return config;
    }

    public void setConfig(WxJsapiConfig config) {
        this.config = config;
    }
}
