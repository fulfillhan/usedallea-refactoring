package com.application.usedallea.member.v2.dto;

import com.application.usedallea.member.v2.domain.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserModifyDTO {

    private String userId;

    private String nickname;

    private String email;

    private String emailstsYn;

    private String phoneNumber;

    private String smsstsYn;

    private LocalDateTime updatedAt;

    private String roadAddress;

    private String jibunAddress;

    private String namujiAddress;

    private String zipCode;

    public static UserModifyDTO toDTO(User user){
        UserModifyDTO userDTO = new UserModifyDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setNickname(user.getNickname());
        userDTO.setEmail(user.getEmail());
        userDTO.setEmailstsYn(user.getEmailstsYn());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setSmsstsYn(user.getSmsstsYn());
        userDTO.setRoadAddress(user.getRoadAddress());
        userDTO.setJibunAddress(user.getJibunAddress());
        userDTO.setNamujiAddress(user.getNamujiAddress());
        userDTO.setZipCode(user.getZipCode());
        return userDTO;
    }
}
