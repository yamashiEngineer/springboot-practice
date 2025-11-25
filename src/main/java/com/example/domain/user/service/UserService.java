package com.example.domain.user.service;

import java.util.List;

import com.example.domain.user.model.MUser;

public interface UserService {

  // ユーザー登録
  public void signup(MUser user);

  // ユーザー一覧取得
  public List<MUser> getUsers();
}
