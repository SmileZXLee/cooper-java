package cn.zxlee.cooper.core.wx.handler;

import cn.zxlee.cooper.core.wx.business.decrypt.entity.WxDecryptResult;
import cn.zxlee.cooper.core.wx.business.login.WxMiniprogramLogin;
import cn.zxlee.cooper.core.wx.business.login.entity.WxMiniprogramLoginResult;
import cn.zxlee.cooper.core.wx.handler.base.AbstractWxMpHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: cooper
 * @description: 微信小程序Handler
 * @author: zxlee
 * @create: 2022-07-17 17:26
 **/

@Component
public class WxMiniprogramHandler extends AbstractWxMpHandler {

    @Autowired
    private WxMiniprogramLogin mpLogin;

    @Override
    public WxMiniprogramLoginResult login(String code) {
        return mpLogin.login(code);
    }

    public WxDecryptResult getUserInfoWithCode(String code, String encryptedData, String iv) {
        return decryptHandler.decryptWithCode(code, encryptedData, iv);
    }

    public WxDecryptResult getUserInfoWithSessionKey(String sessionKey, String encryptedData, String iv) {
        return decryptHandler.decryptWithSessionKey(sessionKey, encryptedData, iv);
    }

    public WxDecryptResult getPhoneNumberWithCode(String code, String encryptedData, String iv) {
        return decryptHandler.decryptWithCode(code, encryptedData, iv);
    }

    public WxDecryptResult getPhoneNumberWithSessionKey(String sessionKey, String encryptedData, String iv) {
        return decryptHandler.decryptWithSessionKey(sessionKey, encryptedData, iv);
    }
}
