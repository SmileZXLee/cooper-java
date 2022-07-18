package cn.zxlee.cooper.core.wx.business.login;

import cn.zxlee.cooper.config.wx.WxMiniprogramProperties;
import cn.zxlee.cooper.core.wx.business.login.base.AbstractWxMpLogin;
import cn.zxlee.cooper.core.wx.business.login.entity.WxMiniprogramLoginResult;
import cn.zxlee.cooper.utils.HttpReqUtils;
import cn.zxlee.cooper.utils.jsonConverter.IJsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: cooper
 * @description: 微信小程序登录
 * @author: zxlee
 * @create: 2022-07-16 17:53
 **/

@Component
public class WxMiniprogramLogin extends AbstractWxMpLogin {
    @Resource
    private WxMiniprogramProperties config;

    @Autowired
    private IJsonConverter jsonConverter;

    @Override
    public WxMiniprogramLoginResult login(String code) {
        Map<String, String> getMap = new HashMap<>();
        getMap.put("appid", config.getAppid());
        getMap.put("secret", config.getSecret());
        getMap.put("grant_type", "authorization_code");
        getMap.put("js_code", code);

        String resultStr = HttpReqUtils.doGet(config.getLoginUrl(), getMap);
        Map<String, Object> innerMap = jsonConverter.str2Map(resultStr);

        WxMiniprogramLoginResult result = new WxMiniprogramLoginResult();
        result.setRawData(innerMap);
        if (innerMap.get("errcode") == null || (int)innerMap.get("errcode") == 0){
            // 微信小程序登录成功
            result.setCode(0);
            result.setOpenid((String)innerMap.get("openid"));
            result.setSessionKey((String)innerMap.get("session_key"));
            result.setUnionid((String)innerMap.get("unionid"));
        } else {
            // 微信小程序登录失败
            result.setCode(((Integer)innerMap.get("errcode")).longValue());
            result.setErrMsg((String)innerMap.get("errmsg"));
        }
        return result;
    }
}
