package cn.zxlee.cooper.core.wx.business.decrypt;

import cn.zxlee.cooper.core.wx.business.login.WxMpLogin;
import cn.zxlee.cooper.core.wx.business.login.entity.WxMpLoginResult;
import cn.zxlee.cooper.exception.CooperDecryptFailedException;
import cn.zxlee.cooper.utils.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @program: cooper
 * @description: 微信信息解密类
 * @author: zxlee
 * @create: 2022-07-16 19:43
 **/

@Component
public class WxDecrypt {
    @Autowired
    WxMpLogin mpLogin;

    public Map<String,Object> decryptWithCode (String code, String encryptedData, String iv){
        WxMpLoginResult result = mpLogin.login(code);
        if (result.getCode() == 0) {
            String sessionKey = result.getSessionKey();
            return decryptWithSessionKey(sessionKey, encryptedData, iv);
        } else {
            throw new CooperDecryptFailedException("通过用户code登录失败，因此解密失败，登录失败原因为：" + result.getErrMsg());
        }
    }

    public Map<String,Object> decryptWithSessionKey (String sessionKey, String encryptedData, String iv){
        return EncryptionUtils.wxDecryptMap(sessionKey, encryptedData, iv);
    }
}
