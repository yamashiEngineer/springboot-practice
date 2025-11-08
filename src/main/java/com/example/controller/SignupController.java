package com.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/user")
public class SignupController {

  @Autowired
  private com.example.applicationservice.UserApplicationService userApplicationService;

  // ユーザー登録画面を表示
  @GetMapping("/signup")
  public String getSignup(Model model) {
    // 性別を取得
    Map<String, Object> genderMap = userApplicationService.getGenderMap();
    model.addAttribute("genderMap", genderMap);

    // ユーザー登録画面に遷移
    return "user/signup";
  }

  @PostMapping("/signup")
  public String postSignup() {

    // ログイン画面にリダイレクト
    return "redirect:/login";
  }

}
