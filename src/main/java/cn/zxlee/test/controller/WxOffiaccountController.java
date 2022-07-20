package cn.zxlee.test.controller;

import cn.zxlee.cooper.core.wx.business.jsapi.config.entity.WxJsapiConfigResult;
import cn.zxlee.cooper.core.wx.service.WxOffiaccountService;
import cn.zxlee.test.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: cooper
 * @description: 微信公众号测试控制器
 * @author: zxlee
 * @create: 2022-07-17 19:33
 **/

@RestController
@RequestMapping("/wx/offiaccount")
public class WxOffiaccountController {
    @Autowired
    WxOffiaccountService offiaccountService;

    @GetMapping("/getJsapiConfig")
    public Result getJsapiConfig(String url){
        WxJsapiConfigResult jsapiConfig = offiaccountService.getJsapiConfig(url);
        if (0 == jsapiConfig.getCode()) {
            // 获取jsapi配置信息成功
            Map<String, Object> map = new HashMap<>();
            map.put("config", jsapiConfig.getConfig());
            return Result.success(map);
        } else {
            // 获取jsapi配置信息失败
            return Result.fail(jsapiConfig.getErrMsg());
        }
    }
}
