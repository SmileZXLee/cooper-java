package cn.zxlee.cooper.config.aliyun;

import cn.zxlee.cooper.config.aliyun.base.AbstractAliyunProperties;
import cn.zxlee.cooper.exception.CooperRequireInfoException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @program: cooper
 * @description:
 * @author: zxlee
 * @create: 2022-07-20 21:58
 **/

@Component
@ConfigurationProperties(prefix = "cooper.aliyun.sms")
public class AliyunSendSmsProperties extends AbstractAliyunProperties {

    /**
     * 阿里云短信的accessKeyId
     */
    private String accessKeyId;

    /**
     * 阿里云短信的accessKeySecret
     */
    private String accessKeySecret;

    /**
     * 阿里云短信的签名名称(非必填)(如果是中文，必须经过unicode编码，生成形如\u4f60\u597d的字符串再填入，否则会乱码，在线unicode编码工具：https://c.runoob.com/front-end/3602/)
     */
    private String signName;

    /**
     * 阿里云短信的模板code(非必填)
     */
    private String templateCode;

    public String getAccessKeyId() {
        if (!StringUtils.hasText(accessKeyId)) {
            throw new CooperRequireInfoException("阿里云短信accessKeyId未配置");
        }
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        if (!StringUtils.hasText(accessKeyId)) {
            throw new CooperRequireInfoException("阿里云短信accessKeySecret未配置");
        }
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getSignName() {
        if (!StringUtils.hasText(signName)) {
            throw new CooperRequireInfoException("阿里云短信签名名称未配置");
        }
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getTemplateCode() {
        if (!StringUtils.hasText(templateCode)) {
            throw new CooperRequireInfoException("阿里云短信模板code未配置");
        }
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }
}
