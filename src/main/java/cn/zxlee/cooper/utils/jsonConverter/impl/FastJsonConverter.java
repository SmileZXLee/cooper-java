package cn.zxlee.cooper.utils.jsonConverter.impl;

import cn.zxlee.cooper.utils.jsonConverter.IJsonConverter;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @program: cooper
 * @description: FastJson实现类
 * @author: zxlee
 * @create: 2022-07-17 21:07
 **/

@Component
public class FastJsonConverter implements IJsonConverter {
    @Override
    public Map<String, Object> str2Map(String str) {
        return JSON.parseObject(str).getInnerMap();
    }

    @Override
    public Map<String, Object> obj2Map(Object obj) {
        return (Map<String, Object>)JSON.toJSON(obj);
    }

    @Override
    public String obj2Str(Object obj) {
        return JSON.toJSONString(obj);
    }

    @Override
    public String map2Str(Map<String, Object> map) {
        return JSON.toJSONString(map);
    }

    @Override
    public <T> T map2Obj (Map<String, Object> map, Class<T> cls) {
        String jsonStr = JSON.toJSONString(map);
        return JSON.parseObject(jsonStr, cls);
    }
}
