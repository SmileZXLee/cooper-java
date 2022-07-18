package cn.zxlee.cooper.core.wx.business.accessToken;

import cn.zxlee.cooper.config.wx.base.AbstractWxMpProperties;
import cn.zxlee.cooper.config.wx.WxMiniprogramProperties;
import cn.zxlee.cooper.config.wx.WxOffiaccountProperties;
import cn.zxlee.cooper.core.wx.business.accessToken.entity.WxAccessTokenResult;
import cn.zxlee.cooper.core.wx.enums.WxAccessTokenTarget;
import cn.zxlee.cooper.utils.HttpReqUtils;
import cn.zxlee.cooper.utils.jsonConverter.IJsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: cooper
 * @description: accessToken生成类
 * @author: zxlee
 * @create: 2022-07-16 16:46
 **/

@Component
public class WxAccessTokenGenerator {
    @Resource
    private WxMiniprogramProperties miniprogramConfig;

    @Resource
    private WxOffiaccountProperties offiaccountConfig;

    @Autowired
    private IJsonConverter jsonConverter;

    private volatile WxAccessTokenResult accessToken;

    public WxAccessTokenResult getAccessToken(WxAccessTokenTarget target) {
        if (accessToken != null && accessToken.getCode() == 0) {
            synchronized (WxAccessTokenResult.class) {
                if (accessToken != null && accessToken.getCode() == 0) {
                    long currentTimeMillis = System.currentTimeMillis();
                    long expireIn = accessToken.getExpiresIn();
                    long createTime = accessToken.getCreateTime();
                    if (createTime + expireIn * 1000 < currentTimeMillis) {
                        // accessToken过期
                        accessToken = createAccessToken(target);
                    }
                } else {
                    // 无可用的accessToken
                    accessToken = createAccessToken(target);
                }
            }
        } else {
            // 无可用的accessToken
            accessToken = createAccessToken(target);
        }
        return accessToken;
    }

    private WxAccessTokenResult createAccessToken (WxAccessTokenTarget target){
        WxAccessTokenResult accessTokenResult = new WxAccessTokenResult();
        Map<String, String> getMap = new HashMap<>();
        AbstractWxMpProperties config = null;
        if (WxAccessTokenTarget.MINIPROGRAM.equals(target)) {
            config = miniprogramConfig;
        } else if (WxAccessTokenTarget.OFFIACCOUNT.equals(target)) {
            config = offiaccountConfig;
        }
        getMap.put("grant_type", "client_credential");
        getMap.put("appid", config.getAppid());
        getMap.put("secret", config.getSecret());
        String result = HttpReqUtils.doGet(config.getAccessTokenUrl(), getMap);
        Map<String, Object> innerMap = jsonConverter.str2Map(result);
        accessTokenResult.setRawData(innerMap);
        if (innerMap.containsKey("access_token")) {
            accessTokenResult.setCode(0);
            accessTokenResult.setAccessToken((String)innerMap.get("access_token"));
            accessTokenResult.setExpiresIn(((Integer)innerMap.get("expires_in")).longValue());
            accessTokenResult.setCreateTime(System.currentTimeMillis());
        } else {
            accessTokenResult.setCode(((Integer)innerMap.get("errcode")).longValue());
            accessTokenResult.setErrMsg((String)innerMap.get("errmsg"));
        }

        return accessTokenResult;
    }
}
