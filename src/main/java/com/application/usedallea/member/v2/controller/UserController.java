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

  /*  @PostMapping("/register")
    public String register(@ModelAttribute UserRegisterDTO userDTO) {
        userService.registerUser(userDTO);
        // 홈페이지 만들기 전까지 로그인 페이지로 이동
        return "redirect:/users/update";
    }*/

    //ajax 적용
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterDTO userDTO) {
        userService.registerUser(userDTO);

        return ResponseEntity.ok("회원 가입 성공");
    }

    @PostMapping("/check-duplicate-id")
    public ResponseEntity<Boolean> checkDuplicateId(@RequestParam String userId){
        boolean isDuplicate = userService.checkDuplicatedUser(userId);
        return ResponseEntity.ok(isDuplicate);
    }
/*    @GetMapping("/details/{userId}")
    public String update(HttpSession session){
        String userId = (String) session.getAttribute("userId");
        if(userId != null){
            UserModifyDTO userDTO = userService.getDetailUser(userId);
        }
        return "member/registerOrUpdate/"+ userId;
    }*/

    @GetMapping("/details/{userId}")
    public ResponseEntity<UserModifyDTO> update(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            ResponseEntity.notFound().build();
        }

        UserModifyDTO userDTO = userService.getDetailUser(userId);

        return ResponseEntity.ok(userDTO);
    }

    @PatchMapping("/update")
    public String update(@ModelAttribute UserModifyDTO userDto, HttpSession session) {
        String userId= (String) session.getAttribute("userId");
        userDto.setUserId(userId);
        userService.updateUser(userDto);
        return "redirect:/edit";
    }

    @PostMapping("/updateActivate")
    public String deactivate(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        userService.deactivateUser(userId);
        session.invalidate();
        return "redirect:/usedallea/main";
    }

}
