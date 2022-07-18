package cn.zxlee.cooper.core.wx.business.decrypt;

import cn.zxlee.cooper.core.wx.business.decrypt.entity.WxDecryptResult;
import cn.zxlee.cooper.core.wx.enums.WxCommonErrorInfo;
import cn.zxlee.cooper.exception.CooperDecryptFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @program: cooper
 * @description: 微信信息解密Handler
 * @author: zxlee
 * @create: 2022-07-18 11:43
 **/

@Component
public class WxDecryptHandler {
    private static final String DECYPT_BY_CODE = "DECYPT_BY_CODE";
    private static final String DECYPT_BY_SESSION_KEY = "DECYPT_BY_SESSION_KEY";

    @Autowired
    protected WxDecrypt decrypt;

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
