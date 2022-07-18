package cn.zxlee.cooper.core.wx.business.getUserInfo;

import cn.zxlee.cooper.config.wx.WxOffiaccountProperties;
import cn.zxlee.cooper.core.wx.business.getUserInfo.base.AbstractWxMpGetUserInfo;
import cn.zxlee.cooper.core.wx.business.getUserInfo.entity.WxOffiaccountGetUserInfoResult;
import cn.zxlee.cooper.core.wx.business.getUserInfo.entity.WxOffiaccountUserInfo;
import cn.zxlee.cooper.core.wx.business.login.WxOffiaccountLogin;
import cn.zxlee.cooper.core.wx.business.login.entity.WxOffiaccountLoginResult;
import cn.zxlee.cooper.utils.HttpReqUtils;
import cn.zxlee.cooper.utils.jsonConverter.IJsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: cooper
 * @description: 微信公众号获取用户信息
 * @author: zxlee
 * @create: 2022-07-18 11:36
 **/

@Component
public class WxOffiaccountGetUserInfo extends AbstractWxMpGetUserInfo {
    private static final String LANG = "zh_CN";

    @Resource
    private WxOffiaccountProperties config;

    @Autowired
    private IJsonConverter jsonConverter;

    @Autowired
    private WxOffiaccountLogin offiaccountLogin;

    public WxOffiaccountGetUserInfoResult getUserInfo(String accessToken, String openid, String lang) {
        WxOffiaccountGetUserInfoResult result = new WxOffiaccountGetUserInfoResult();

        Map<String, String> getMap = new HashMap<>();
        getMap.put("access_token", accessToken);
        getMap.put("openid", openid);
        getMap.put("lang", lang);

        String resultStr = HttpReqUtils.doGet(config.getGetUserInfoUrl(), getMap);
        Map<String, Object> innerMap = jsonConverter.str2Map(resultStr);

        result.setRawData(innerMap);
        if (innerMap.get("errcode") == null || (int)innerMap.get("errcode") == 0){
            // 获取微信公众用户信息成功
            result.setCode(0);
            result.setUserInfo(jsonConverter.map2Obj(innerMap, WxOffiaccountUserInfo.class));
        } else {
            // 获取微信公众用户信息失败
            result.setCode(((Integer)innerMap.get("errcode")).longValue());
            result.setErrMsg((String)innerMap.get("errmsg"));
        }
        return result;
    }

    public WxOffiaccountGetUserInfoResult getUserInfo(String accessToken, String openid) {
        return getUserInfo(accessToken, openid, LANG);
    }

    public WxOffiaccountGetUserInfoResult getUserInfo(String code) {
        WxOffiaccountLoginResult loginResult = offiaccountLogin.login(code);
        if (loginResult.getCode() == 0) {
            return getUserInfo(loginResult.getAccessToken(), loginResult.getOpenid());
        } else {
            WxOffiaccountGetUserInfoResult result = new WxOffiaccountGetUserInfoResult();
            result.setCode(loginResult.getCode());
            result.setErrMsg("获取公众号用户信息失败，因为用户登录失败，失败原因为：" + loginResult.getErrMsg());
            return result;
        }
    }
}
