package com.zxa.service;

import com.zxa.entity.User;

public interface UserService {

    boolean emailIsExist(String email);

    void registerUser(User user);

    User getUserByEmail(String email);

    int update(User user);
}

