package com.application.usedallea.member.v2.controller;

import com.application.usedallea.member.v2.dto.UserModifyDTO;
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
public class UserController {

    private final UserService userService;

    @GetMapping("/register-page")
    public String toRegisterPage() {
        return "member/registerOrUpdate";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserRegisterDTO userDTO) {
        userService.registerUser(userDTO);
        // 홈페이지 만들기 전까지 로그인 페이지로 이동
        return "redirect:/login-form";
    }

    @PostMapping("/check-duplicate-id")
    public ResponseEntity<Boolean> checkDuplicateId(@RequestParam String userId){
        boolean isDuplicate = userService.checkDuplicatedUser(userId);
        return ResponseEntity.ok(isDuplicate);
    }
    @GetMapping("/edit")
    public String edit(HttpSession session){
        String userId = (String) session.getAttribute("userId");
        return "member/registerOrUpdate/"+ userId;
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute UserModifyDTO userDto, HttpSession session) {
        String userId= (String) session.getAttribute("userId");
        userDto.setUserId(userId);
        userService.updateUser(userDto);
        return "redirect:/edit";
    }

    @PostMapping
    public String deactivate(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        userService.deactivateUser(userId);
        session.invalidate();
        return "redirect:/usedallea/main";
    }

}
