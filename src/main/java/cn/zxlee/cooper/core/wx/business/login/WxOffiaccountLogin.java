package cn.zxlee.cooper.core.wx.business.login;

import cn.zxlee.cooper.config.wx.WxOffiaccountProperties;
import cn.zxlee.cooper.core.wx.business.login.base.AbstractWxMpLogin;
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
 * @description: 微信公众号登录
 * @author: zxlee
 * @create: 2022-07-18 11:06
 **/

@Component
public class WxOffiaccountLogin extends AbstractWxMpLogin {
    @Resource
    private WxOffiaccountProperties config;

    @Autowired
    private IJsonConverter jsonConverter;

    @Override
    public WxOffiaccountLoginResult login(String code) {
        WxOffiaccountLoginResult result = new WxOffiaccountLoginResult();
        Map<String, String> getMap = new HashMap<>();
        getMap.put("appid", config.getAppid());
        getMap.put("secret", config.getSecret());
        getMap.put("code", code);
        getMap.put("grant_type", "authorization_code");

        String resultStr = HttpReqUtils.doGet(config.getLoginUrl(), getMap);
        Map<String, Object> innerMap = jsonConverter.str2Map(resultStr);

        result.setRawData(innerMap);
        if (innerMap.get("errcode") == null || (int)innerMap.get("errcode") == 0){
            // 微信公众号登录成功
            result.setCode(0);
            result.setAccessToken((String)innerMap.get("access_token"));
            result.setExpiresIn(((Integer)innerMap.get("expires_in")).longValue());
            result.setRefreshToken((String)innerMap.get("refresh_token"));
            result.setOpenid((String)innerMap.get("openid"));
            result.setScope((String)innerMap.get("scope"));

        } else {
            // 微信公众号登录失败
            result.setCode(((Integer)innerMap.get("errcode")).longValue());
            result.setErrMsg((String)innerMap.get("errmsg"));
        }

        return result;
    }
}
