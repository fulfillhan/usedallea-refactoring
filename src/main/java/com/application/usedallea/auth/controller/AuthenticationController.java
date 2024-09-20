package com.application.usedallea.auth.controller;


import com.application.usedallea.member.v2.dto.UserRegisterDTO;
import com.application.usedallea.member.v2.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @GetMapping("/login-form")
    public String login() {
        return "member/login";
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody UserRegisterDTO userDto, HttpSession session) {
        boolean validateLogin = userService.validateUser(userDto);
        if (validateLogin) {
            //로그인 성공시 세션 설정
            session.setAttribute("userId", userDto.getUserId());
        }
        return ResponseEntity.ok(validateLogin);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/usedallea/home";
    }
}
