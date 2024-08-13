package com.application.usedallea.member.v2.service;

import com.application.usedallea.member.v2.domain.entity.User;
import com.application.usedallea.member.v2.domain.repository.UserRepository;
import com.application.usedallea.member.v2.dto.UserModifyDto;
import com.application.usedallea.member.v2.dto.UserRegisterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserFactory userFactory;
   private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserRegisterDto userDto) {
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        User newUser = userFactory.createUser(userDto, encodedPassword);
        userRepository.register(newUser);
    }


    @Override
    public void updateUser(UserModifyDto userDto) {
        User user = userRepository.findById(userDto.getUserId());

        if (user == null) {
            throw new UsernameNotFoundException(userDto.getUserId() + "is not found");
        }
        User newUser = userFactory.udpateUser(user, userDto);
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
                .updatedAt(user.getUpdatedAt())
                .build();
        userRepository.update(updateUser);
    }

    @Override
    public boolean validateUser(UserRegisterDto userDto) {
        String userId = userDto.getUserId();
        User foundUser = userRepository.findById(userId);

        if (foundUser == null) {
            return false;
        }

        boolean equalsPassword = passwordEncoder.matches(userDto.getPassword(), foundUser.getPassword());
        boolean activatedUser = foundUser.getActiveYn().equals("y");
        return equalsPassword && activatedUser;
    }

@Override
public boolean checkDuplicatedUser(String userId) {
    // 중복 여부
    return userRepository.isExistById(userId);

    }
}
