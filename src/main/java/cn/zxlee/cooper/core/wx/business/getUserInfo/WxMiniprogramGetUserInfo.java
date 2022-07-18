package cn.zxlee.cooper.core.wx.business.getUserInfo;

import cn.zxlee.cooper.core.wx.business.decrypt.WxDecryptHandler;
import cn.zxlee.cooper.core.wx.business.decrypt.entity.WxDecryptResult;
import cn.zxlee.cooper.core.wx.business.getUserInfo.base.AbstractWxMpGetUserInfo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: cooper
 * @description: 微信小程序获取用户信息
 * @author: zxlee
 * @create: 2022-07-18 11:36
 **/
public class WxMiniprogramGetUserInfo extends AbstractWxMpGetUserInfo {
    @Autowired
    protected WxDecryptHandler decryptHandler;

    public WxDecryptResult getUserInfoWithCode(String code, String encryptedData, String iv) {
        return decryptHandler.decryptWithCode(code, encryptedData, iv);
    }

    public WxDecryptResult getUserInfoWithSessionKey(String sessionKey, String encryptedData, String iv) {
        return decryptHandler.decryptWithSessionKey(sessionKey, encryptedData, iv);
    }
}
