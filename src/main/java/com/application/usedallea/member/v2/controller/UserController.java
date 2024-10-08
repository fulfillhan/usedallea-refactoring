package com.application.usedallea.member.v2.controller;

import com.application.usedallea.member.v2.dto.UserModifyDTO;
import com.application.usedallea.member.v2.dto.UserRegisterDTO;
import com.application.usedallea.member.v2.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register-page")
    public String toRegisterPage() {
        return "/member/registerOrUpdate";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserRegisterDTO userDTO) {
        userService.registerUser(userDTO);
        return "redirect:/usedallea/home";
    }

    @PostMapping("/check-duplicate-id")
    public ResponseEntity<Boolean> checkDuplicateId(@RequestParam String userId){
        boolean isDuplicate = userService.checkDuplicatedUser(userId);
        return ResponseEntity.ok(isDuplicate);
    }
    @GetMapping("/update")
    public String update(HttpSession session, Model model){
        String userId = (String) session.getAttribute("userId");
        if(userId != null){
            UserModifyDTO userDetail = userService.getDetailUser(userId);
            if(userDetail == null){
                userDetail = new UserModifyDTO();
            }
            model.addAttribute("memberDTO",userDetail);
        }
        return "member/registerOrUpdate";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute UserModifyDTO userDto, HttpSession session) {
        String userId= (String) session.getAttribute("userId");
        userDto.setUserId(userId);
        userService.updateUser(userDto);
        return "redirect:/usedallea/home";  // 추후 홈으로 변경 필요
    }

    @PostMapping("/updateActivate")
    public String deactivate(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        userService.deactivateUser(userId);
        session.invalidate();
        return "redirect:/usedallea/main";
    }
}
