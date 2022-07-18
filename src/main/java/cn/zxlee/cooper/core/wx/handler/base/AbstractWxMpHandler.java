package cn.zxlee.cooper.core.wx.handler.base;

import cn.zxlee.cooper.base.entity.BaseResult;
import cn.zxlee.cooper.core.wx.business.decrypt.WxDecryptHandler;
import cn.zxlee.cooper.core.wx.business.decrypt.entity.WxDecryptResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @program: cooper
 * @description: 抽象的微信小程序或公众号处理者
 * @author: zxlee
 * @create: 2022-07-17 22:29
 **/
public abstract class AbstractWxMpHandler {

    @Autowired
    protected WxDecryptHandler decryptHandler;

    public abstract BaseResult login(String code);

    public WxDecryptResult decryptWithCode(String code, String encryptedData, String iv) {
        return decryptHandler.decryptWithCode(code, encryptedData, iv);
    }

    public WxDecryptResult decryptWithSessionKey(String sessionKey, String encryptedData, String iv) {
        return decryptHandler.decryptWithSessionKey(sessionKey, encryptedData, iv);
    }
}
