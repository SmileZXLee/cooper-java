package cn.zxlee.cooper.core.wx.handler;

import cn.zxlee.cooper.core.wx.business.jsapi.config.WxJsapiConfigGenerator;
import cn.zxlee.cooper.core.wx.business.jsapi.config.entity.WxJsapiConfigResult;
import cn.zxlee.cooper.core.wx.handler.base.AbstractWxMpHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: cooper
 * @description: 微信公众号处理者
 * @author: zxlee
 * @create: 2022-07-17 22:22
 **/

@Component
public class WxOffiaccountHandler extends AbstractWxMpHandler {
    @Autowired
    private WxJsapiConfigGenerator jsapiConfigGenerator;

    public WxJsapiConfigResult getJsapiConfig(String url) {
        return jsapiConfigGenerator.getConfig(url);
    }
}
