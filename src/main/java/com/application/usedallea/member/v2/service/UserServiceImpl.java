package com.application.usedallea.member.v2.service;

import com.application.usedallea.member.v2.domain.entity.User;
import com.application.usedallea.member.v2.domain.repository.UserRepository;
import com.application.usedallea.member.v2.dto.UserModifyDTO;
import com.application.usedallea.member.v2.dto.UserRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void registerUser(UserRegisterDTO userDTO) {
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());

         User newUser = User.builder()
                .userId(userDTO.getUserId())
                .nickname(userDTO.getNickname())
                .password(encodedPassword)
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .emailstsYn(Optional.ofNullable(userDTO.getEmailstsYn()).orElse("n"))
                .phoneNumber(userDTO.getPhoneNumber())
                .smsstsYn(Optional.ofNullable(userDTO.getSmsstsYn()).orElse("n"))
                .roadAddress(userDTO.getRoadAddress())
                .jibunAddress(userDTO.getJibunAddress())
                .namujiAddress(userDTO.getNamujiAddress())
                .zipCode(userDTO.getZipCode())
                .personalinfoYn(Optional.ofNullable(userDTO.getPersonalinfoYn()).orElse("n"))
                .build();

        userRepository.register(newUser);
    }


    @Override
    public void updateUser(UserModifyDTO userDTO) {
        User user = userRepository.findById(userDTO.getUserId());

        if (user == null) {
            throw new UsernameNotFoundException(userDTO.getUserId() + "is not found");
        }

        User newUser = user.toBuilder()
                .nickname(userDTO.getNickname())
                .smsstsYn(Optional.ofNullable(userDTO.getSmsstsYn()).orElse("n"))
                .phoneNumber(userDTO.getPhoneNumber())
                .email(userDTO.getEmail())
                .emailstsYn(Optional.ofNullable(userDTO.getEmailstsYn()).orElse("n"))
                .zipCode(userDTO.getZipCode())
                .roadAddress(userDTO.getRoadAddress())
                .jibunAddress(userDTO.getJibunAddress())
                .namujiAddress(userDTO.getNamujiAddress())
                .build();

        userRepository.update(newUser);
    }

    @Override
    public void deactivateUser(String userId) {
        User user = userRepository.findById(userId);

        if (user == null) {
            return;
        }

        User updateUser = user.toBuilder()
                .activeYn("n")
                //.updatedAt(user.getUpdatedAt())
                .build();
        userRepository.updateDeactivate(updateUser);
    }

    @Override
    public boolean validateUser(UserRegisterDTO userDto) {
        String userId = userDto.getUserId();
        User user = userRepository.findById(userId);

        if (user == null) {
            return false;
        }

        boolean equalsPassword = passwordEncoder.matches(userDto.getPassword(), user.getPassword());
        boolean activatedUser = user.getActiveYn().equals("y");
        return equalsPassword && activatedUser;
    }

    @Override
    public boolean checkDuplicatedUser(String userId) {
        boolean isDuplicate = false;
        User user = userRepository.findById(userId);
        if(user != null && user.getUserId() != null){
            isDuplicate = true;
        }
        return isDuplicate;
    }

    @Override
    public UserModifyDTO getDetailUser(String userId) {
        User user = userRepository.findById(userId);

        if (user == null) {
            throw new UsernameNotFoundException(userId + "is not found");
        }
        return UserModifyDTO.toDTO(user);
    }
}
