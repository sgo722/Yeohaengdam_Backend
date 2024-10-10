package com.ssafy.yeohaengdam.user.controller;

import com.ssafy.yeohaengdam.core.annotation.CurrentUser;
import com.ssafy.yeohaengdam.user.entity.User;
import com.ssafy.yeohaengdam.user.service.UserService;
import jakarta.mail.Multipart;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Map;

import static com.ssafy.yeohaengdam.user.dto.UserData.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@EnableWebSecurity
public class UserController {

    private final UserService userService;

    /**
     * 회원가입
     * @param join
     */
    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public void join(@RequestBody Join join){
        userService.join(join);
    }

    /**
     * 닉네임 중복 검증
     */
    @GetMapping("/check_nickname/{nickname}")
    public ResponseEntity<?> checkNickname(@PathVariable(value = "nickname") String nickname){
        boolean isAvailable = userService.checkNickname(nickname);
        System.out.println("중복 조회" + nickname);
        if(!isAvailable){
            return ResponseEntity.ok().body(Map.of("available", false, "message", "Nickname is already taken"));
        }
        return ResponseEntity.ok().body(Map.of("available", true, "message", "Nickname is available"));
    }



    @PutMapping("/update_password")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> updatePassword(@CurrentUser User user,
                                       @RequestBody Password password){

        userService.updatePassword(user, password);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update_nickname")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> updateNickname(@CurrentUser User user,
                                       @RequestBody Update update){

        System.out.println(update);
        userService.updateNickname(user, update);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/updateProfile")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> updateProfile(@CurrentUser User user,
                                              @RequestPart MultipartFile image){
        System.out.println(image);
        userService.updateImage(user, image);
        return ResponseEntity.ok().build();
    }

    /**
     * 비밀번호 찾기
     * @param password
     * @return
     */
    @PatchMapping("/update/password")
    public ResponseEntity<Void> updatePassword(@RequestBody Password password){
        userService.updatePassword(password);
        return ResponseEntity.ok().build();
    }

    /**
     * 비밀번호 초기화
     */
    @PostMapping("/reset/password")
    public ResponseEntity<String> resetPassword(@RequestBody Password password){
        return ResponseEntity.ok(userService.resetPassword(password));

    }


    /**
     * 회원 정보 조회
     */
    @GetMapping("/myInfo")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> myPage(@CurrentUser User user){
        System.out.println("member info : " + user);
//        User user = userService.findByEmail(user.getEmail());
        return ResponseEntity.ok(user);
    }

    /**
     * 회원 삭제
     */
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> delete(@RequestBody UserInfo userInfo){
        userService.delete(userInfo);
        return ResponseEntity.ok().build();
    }
}
