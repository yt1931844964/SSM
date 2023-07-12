package com.qtds.util;

import com.alibaba.fastjson.JSON;

public class JsonUtil {

    public static String obj2String(Object o){
        return JSON.toJSONString(o);
    }

}
