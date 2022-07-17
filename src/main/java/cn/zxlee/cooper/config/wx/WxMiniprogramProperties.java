package cn.zxlee.cooper.config.wx;

import cn.zxlee.cooper.config.wx.base.WxMpProperties;
import cn.zxlee.cooper.exception.CooperRequireInfoException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @program: cooper
 * @description:
 * @author: zxlee
 * @create: 2022-07-16 20:23
 **/

@Component
@ConfigurationProperties(prefix = "cooper.wx.miniprogram")
public class WxMiniprogramProperties extends WxMpProperties {
    @Override
    public String getAppid() {
        if (!StringUtils.hasText(appid)) {
            throw new CooperRequireInfoException("微信小程序appid未配置");
        }
        return appid;
    }

    @Override
    public String getSecret() {
        if (!StringUtils.hasText(secret)) {
            throw new CooperRequireInfoException("微信小程序secret未配置");
        }
        return secret;
    }
}
