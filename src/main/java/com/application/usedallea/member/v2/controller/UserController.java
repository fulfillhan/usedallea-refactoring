package com.application.usedallea.member.v2.controller;

import com.application.usedallea.member.v2.dto.UserModifyDTO;
import com.application.usedallea.member.v2.dto.UserRegisterDTO;
import com.application.usedallea.member.v2.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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
        return "redirect:/usedallea/main";
    }
// TODO HTML 확인필요
    @PostMapping("/check-duplicate-id")
    @ResponseBody
    public boolean checkDuplicateId(@RequestParam String userId){
        return userService.checkDuplicatedUser(userId);
    }

    @PutMapping
    public String update(@ModelAttribute UserModifyDTO userDto, HttpSession session) {
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
