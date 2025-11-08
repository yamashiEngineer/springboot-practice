package com.example.applicationservice;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class UserApplicationService {

  // 性別のMapを生成する
  public Map<String, Object> getGenderMap() {
    Map<String, Object> genderMap = new LinkedHashMap<>();
    genderMap.put("男性", 1);
    genderMap.put("女性", 2);
    return genderMap;
  }
}