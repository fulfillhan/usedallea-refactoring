package com.application.usedallea.member.v2.service;

import com.application.usedallea.member.v2.domain.entity.User;
import com.application.usedallea.member.v2.dto.UserModifyDto;
import com.application.usedallea.member.v2.dto.UserRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserFactory {


    public User createUser(UserRegisterDto userDto, String password) {
        return User.builder()
                .userId(userDto.getUserId())
                .nickname(userDto.getNickname())
                .password(password)
                .name(userDto.getName())
                .activeYn(userDto.getActiveYn())
                .email(userDto.getEmail())
                .emailStsYn(Optional.ofNullable(userDto.getEmailStsYn()).orElse("n"))
                .phoneNumber(userDto.getPhoneNumber())
                .smsStsYn(Optional.ofNullable(userDto.getSmsStsYn()).orElse("n"))
                .roadAddress(userDto.getRoadAddress())
                .jibunAddress(userDto.getJibunAddress())
                .namujiAddress(userDto.getNamujiAddress())
                .zipCode(userDto.getZipCode())
                .personalInfYn(Optional.ofNullable(userDto.getPersonalInfYn()).orElse("n"))
                .build();
    }

    public User udpateUser(User existingUser, UserModifyDto userDto) {
        return existingUser.toBuilder()
                .nickname(userDto.getNickname())
                .smsStsYn(Optional.ofNullable(userDto.getSmsStsYn()).orElse("n"))
                .phoneNumber(userDto.getPhoneNumber())
                .email(userDto.getEmail())
                .emailStsYn(Optional.ofNullable(userDto.getEmailStsYn()).orElse("n"))
                .zipCode(userDto.getZipCode())
                .roadAddress(userDto.getRoadAddress())
                .jibunAddress(userDto.getJibunAddress())
                .namujiAddress(userDto.getNamujiAddress())
                .build();
    }
}
