package cn.zxlee.cooper.core.wx.handler.base;

import cn.zxlee.cooper.core.wx.business.decrypt.WxDecrypt;
import cn.zxlee.cooper.core.wx.business.decrypt.entity.WxDecryptResult;
import cn.zxlee.cooper.core.wx.business.login.WxMpLogin;
import cn.zxlee.cooper.core.wx.business.login.entity.WxMpLoginResult;
import cn.zxlee.cooper.core.wx.enums.WxCommonErrorInfo;
import cn.zxlee.cooper.exception.CooperDecryptFailedException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @program: cooper
 * @description: 抽象的微信小程序或公众号处理者
 * @author: zxlee
 * @create: 2022-07-17 22:29
 **/
public abstract class AbstractWxMpHandler {
    private static final String DECYPT_BY_CODE = "DECYPT_BY_CODE";
    private static final String DECYPT_BY_SESSION_KEY = "DECYPT_BY_SESSION_KEY";

    @Autowired
    WxMpLogin mpLogin;

    @Autowired
    WxDecrypt decrypt;


    public WxMpLoginResult login(String code) {
        return mpLogin.login(code);
    }


    public WxDecryptResult decryptWithCode(String code, String encryptedData, String iv) {
        return decrypt(DECYPT_BY_CODE,code,encryptedData,iv);
    }


    public WxDecryptResult decryptWithSessionKey(String sessionKey, String encryptedData, String iv) {
        return decrypt(DECYPT_BY_SESSION_KEY,sessionKey,encryptedData,iv);
    }

    private WxDecryptResult decrypt(String by, String byKey, String encryptedData, String iv) {
        WxDecryptResult result = new WxDecryptResult();
        try {
            Map<String, Object> decryptResultMap = null;
            if (DECYPT_BY_CODE.equals(by)) {
                decryptResultMap = decrypt.decryptWithCode(byKey, encryptedData, iv);
            } else if (DECYPT_BY_SESSION_KEY.equals(by)) {
                decryptResultMap = decrypt.decryptWithSessionKey(byKey, encryptedData, iv);
            }
            if (decryptResultMap != null) {
                result.setCode(0);
                result.setResult(decryptResultMap);
            } else {
                result.setCode(WxCommonErrorInfo.DECRYPT_FAILED.getCode());
                result.setErrMsg(WxCommonErrorInfo.DECRYPT_FAILED.getMsg());
            }
        } catch (CooperDecryptFailedException e) {
            e.printStackTrace();
            result.setCode(WxCommonErrorInfo.DECRYPT_FAILED.getCode());
            result.setErrMsg(e.getMessage());
        }
        return result;
    }
}
