package cn.zxlee.cooper.core.wx.service;

import cn.zxlee.cooper.core.wx.business.getUserInfo.WxOffiaccountGetUserInfo;
import cn.zxlee.cooper.core.wx.business.getUserInfo.entity.WxOffiaccountGetUserInfoResult;
import cn.zxlee.cooper.core.wx.business.jsapi.config.WxJsapiConfigGenerator;
import cn.zxlee.cooper.core.wx.business.jsapi.config.entity.WxJsapiConfigResult;
import cn.zxlee.cooper.core.wx.business.login.WxOffiaccountLogin;
import cn.zxlee.cooper.core.wx.business.login.entity.WxOffiaccountLoginResult;
import cn.zxlee.cooper.core.wx.service.base.AbstractWxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: cooper
 * @description: 微信公众号Handler
 * @author: zxlee
 * @create: 2022-07-17 22:22
 **/

@Component
public class WxOffiaccountService extends AbstractWxMpService {
    @Autowired
    private WxJsapiConfigGenerator jsapiConfigGenerator;

    @Autowired
    private WxOffiaccountLogin offiaccountLogin;

    @Autowired
    private WxOffiaccountGetUserInfo offiaccountGetUserInfo;

    public WxJsapiConfigResult getJsapiConfig(String url) {
        return jsapiConfigGenerator.getConfig(url);
    }

    @Override
    public WxOffiaccountLoginResult login(String code) {
        return offiaccountLogin.login(code);
    }

    public WxOffiaccountGetUserInfoResult getUserInfo(String accessToken, String openid, String lang) {
        return offiaccountGetUserInfo.getUserInfo(accessToken, openid, lang);
    }

    public WxOffiaccountGetUserInfoResult getUserInfo(String accessToken, String openid) {
        return offiaccountGetUserInfo.getUserInfo(accessToken, openid);
    }

    public WxOffiaccountGetUserInfoResult getUserInfo(String code) {
        return offiaccountGetUserInfo.getUserInfo(code);
    }
}
