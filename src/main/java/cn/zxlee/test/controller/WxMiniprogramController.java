package cn.zxlee.test.controller;

import cn.zxlee.cooper.core.wx.business.decrypt.entity.WxDecryptResult;
import cn.zxlee.cooper.core.wx.service.WxMiniprogramService;
import cn.zxlee.cooper.core.wx.business.login.entity.WxMiniprogramLoginResult;
import cn.zxlee.test.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: cooper
 * @description: 微信小程序测试控制器
 * @author: zxlee
 * @create: 2022-07-17 15:13
 **/

@RestController
@RequestMapping("/wx/miniprogram")
public class WxMiniprogramController {

    @Autowired
    WxMiniprogramService miniprogramService;

    @GetMapping("/login")
    public Result login(String code){
        WxMiniprogramLoginResult login = miniprogramService.login(code);
        if (0 == login.getCode()) {
            // 微信登录成功
            HashMap<String, String> map = new HashMap<>();
            map.put("open_id", login.getOpenid());
            return Result.success(map);
        } else {
            // 微信登录失败
            return Result.fail(login.getErrMsg());
        }
    }

    @PostMapping("/getUserInfo")
    public Result getUserInfo(@RequestBody Map map){
        String code = (String)map.get("code");
        String encryptedData = (String)map.get("encryptedData");
        String iv = (String)map.get("iv");
        WxDecryptResult decryptResult = miniprogramService.getUserInfoWithCode(code, encryptedData, iv);
        if (0 == decryptResult.getCode()) {
            // 用户信息解密成功
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("userInfo", decryptResult.getResult());
            return Result.success(resultMap);
        } else {
            // 用户信息解密成功
            return Result.fail(decryptResult.getErrMsg());
        }
    }

    @PostMapping("/getPhoneNumber")
    public Result getPhoneNumber(@RequestBody Map map){
        String code = (String)map.get("code");
        String encryptedData = (String)map.get("encryptedData");
        String iv = (String)map.get("iv");
        WxDecryptResult decryptResult = miniprogramService.getPhoneNumberWithCode(code, encryptedData, iv);
        if (0 == decryptResult.getCode()) {
            // 用户手机号解密成功
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("phoneInfo", decryptResult.getResult());
            return Result.success(resultMap);
        } else {
            // 用户手机号解密失败
            return Result.fail(decryptResult.getErrMsg());
        }
    }
}
