package com.application.usedallea.member.v2.service;

import com.application.usedallea.member.v2.dto.UserModifyDTO;
import com.application.usedallea.member.v2.dto.UserRegisterDTO;

public interface UserService{

    void registerUser(UserRegisterDTO userRegisterDto);

    void updateUser(UserModifyDTO userModifyDto);

    void deactivateUser(String userId);

    boolean validateUser(UserRegisterDTO userRegisterDto);

    //boolean checkDuplicatedUser(String userId);
    boolean checkDuplicatedUser(String userId);
}
