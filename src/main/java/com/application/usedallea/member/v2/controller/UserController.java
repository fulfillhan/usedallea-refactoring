package com.application.usedallea.member.v2.controller;

import com.application.usedallea.member.v2.dto.UserModifyDto;
import com.application.usedallea.member.v2.dto.UserRegisterDto;
import com.application.usedallea.member.v2.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register-page")
    public String toRegisterPage() {
        return "member/registerOrUpdate";
    }

    @PostMapping
    public String register(@ModelAttribute UserRegisterDto userDto) {
        userService.registerUser(userDto);
        return "redirect:/usedallea/main";
    }

    @PostMapping("/check-duplicate-id")
    public ResponseEntity<Boolean> checkDuplicateId(@RequestParam String userId){
        boolean isDuplicate = userService.checkDuplicatedUser(userId);
        return ResponseEntity.ok(isDuplicate);
    }


    @PutMapping
    public String update(@ModelAttribute UserModifyDto userDto, HttpSession session) {
        String userId= (String) session.getAttribute("userId");
        userDto.setUserId(userId);
        userService.updateUser(userDto);
        return "redirect:/usedallea/main";
    }

    @DeleteMapping
    public String deactivate(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        userService.deactivateUser(userId);
        session.invalidate();
        return "redirect:/usedallea/main";
    }


}
