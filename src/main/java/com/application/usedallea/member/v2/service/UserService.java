package com.application.usedallea.member.v2.service;

import com.application.usedallea.member.v2.dto.UserModifyDto;
import com.application.usedallea.member.v2.dto.UserRegisterDto;

public interface UserService{

    void registerUser(UserRegisterDto userRegisterDto);

    void updateUser(UserModifyDto userModifyDto);

    void deactivateUser(String userId);

    boolean loginUser(UserRegisterDto userRegisterDto);

    boolean checkDuplicatedUser(String userId);
}
