package com.example.studentservice.helpers;

import java.util.HashMap;
import java.util.Map;

public class RegisterHelper {
    Map<String,Object> map = new HashMap<>();

    public Map<String, Object> getMap() {
        map.put("message","Register successfully !");
        return map;
    }
}


