package cn.zxlee.cooper.utils.jsonConverter;

import org.springframework.stereotype.Component;

import java.util.Map;

public interface IJsonConverter {
    Map<String, Object> str2Map(String str);

    Map<String, Object> obj2Map(Object obj);

    String obj2Str(Object obj);

    String map2Str(Map<String, Object> map);

    <T> T map2Obj (Map<String, Object> map, Class<T> cls);
}
