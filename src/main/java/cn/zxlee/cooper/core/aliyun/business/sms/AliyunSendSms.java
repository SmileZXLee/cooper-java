package cn.zxlee.cooper.core.aliyun.business.sms;

import cn.zxlee.cooper.config.aliyun.AliyunSendSmsProperties;
import cn.zxlee.cooper.core.aliyun.business.sms.entity.AliyunSendSmsResult;
import cn.zxlee.cooper.core.wx.enums.aliyun.AliyunCommonErrorInfo;
import cn.zxlee.cooper.utils.RandomUtils;
import cn.zxlee.cooper.utils.jsonConverter.IJsonConverter;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: cooper
 * @description:
 * @author: zxlee
 * @create: 2022-07-19 21:55
 **/

@Component
public class AliyunSendSms {

    @Resource
    private AliyunSendSmsProperties config;

    @Autowired
    private IJsonConverter jsonConverter;

    public AliyunSendSmsResult send(SendSmsRequest sendSmsRequest) {
        AliyunSendSmsResult result = new AliyunSendSmsResult();
        try {
            Client client = null;
            client = createClient(config.getAccessKeyId(), config.getAccessKeySecret());
            RuntimeOptions runtime = new RuntimeOptions();
            SendSmsResponse smsResponse = client.sendSmsWithOptions(sendSmsRequest, runtime);
            if (200 == smsResponse.getStatusCode()) {
                SendSmsResponseBody smsResponseBody = smsResponse.getBody();
                if ("OK".equals(smsResponseBody.getCode())) {
                    result.setCode(0);
                } else {
                    result.setCode(AliyunCommonErrorInfo.SEND_SMS_FAILED.getCode());
                    result.setErrMsg(smsResponseBody.getMessage());
                }
            } else {
                result.setCode(AliyunCommonErrorInfo.SEND_SMS_FAILED.getCode());
                result.setErrMsg(AliyunCommonErrorInfo.SEND_SMS_FAILED.getMsg());
            }

        } catch (TeaException error) {
            result.setCode(AliyunCommonErrorInfo.SEND_SMS_FAILED.getCode());
            result.setErrMsg(error.getMessage());
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            result.setCode(AliyunCommonErrorInfo.SEND_SMS_FAILED.getCode());
            result.setErrMsg(error.getMessage());
        }
        return result;
    }

    public AliyunSendSmsResult send(String signName, String templateCode, String phoneNumbers, String templateParam) {
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName(signName)
                .setTemplateCode(templateCode)
                .setPhoneNumbers(phoneNumbers)
                .setTemplateParam(templateParam);
        return send(sendSmsRequest);
    }

    public AliyunSendSmsResult send(String templateCode, String phoneNumbers, Map<String, Object> templateParam) {
        return send(templateCode, phoneNumbers, jsonConverter.map2Str(templateParam));
    }

    public AliyunSendSmsResult send(String templateCode, String phoneNumbers, String templateParam) {
        return send(config.getSignName(), templateCode, phoneNumbers, templateParam);
    }


    public AliyunSendSmsResult send(String phoneNumbers, Map<String, Object> templateParam) {
        return send(config.getTemplateCode(), phoneNumbers, jsonConverter.map2Str(templateParam));
    }

    public AliyunSendSmsResult sendCheckCode(String signName, String templateCode, String phoneNumbers) {
        Map<String, Object> templateParam = new HashMap<>();
        String checkCode = RandomUtils.getRandomNumber(6);
        templateParam.put("code", checkCode);
        AliyunSendSmsResult result = send(signName, templateCode, jsonConverter.map2Str(templateParam));
        if (0 == result.getCode()) {
            result.setCheckCode(checkCode);
        }
        return result;
    }

    public AliyunSendSmsResult sendCheckCode(String templateCode, String phoneNumbers) {
        return sendCheckCode(config.getSignName(), templateCode, phoneNumbers);
    }

    public AliyunSendSmsResult sendCheckCode(String phoneNumbers) {
        return sendCheckCode(config.getTemplateCode(), phoneNumbers);
    }

    private static Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                .setAccessKeyId(accessKeyId)
                .setAccessKeySecret(accessKeySecret);
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new Client(config);
    }
}
