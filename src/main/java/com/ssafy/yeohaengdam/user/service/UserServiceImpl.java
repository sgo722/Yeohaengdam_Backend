package com.ssafy.yeohaengdam.user.service;

import com.ssafy.yeohaengdam.auth.service.EmailService;
import com.ssafy.yeohaengdam.core.annotation.CurrentUser;
import com.ssafy.yeohaengdam.user.entity.User;
import com.ssafy.yeohaengdam.user.mapper.UserMapper;
import com.ssafy.yeohaengdam.utils.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.function.Function;

import static com.ssafy.yeohaengdam.user.dto.UserData.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final PasswordService passwordService;
    private final S3Service s3Service;
    private final EmailService emailService;

    @Override
    public void join(Join join) {
        User newUser = User.builder()
                .email(join.getEmail())
                .password(passwordEncoder.encode(join.getPassword()))
                .nickname(join.getNickname())
                .build();
        userMapper.join(newUser);

    }

    @Override
    public void updateImage(@CurrentUser User user, @RequestPart MultipartFile image){

        String imageUrl = null;
        if(image != null) {
            try {
                imageUrl = s3Service.upload(image, "user/images");
                userMapper.updateProfile(user.getEmail(), imageUrl);
            } catch (Exception e) {
                throw new RuntimeException("이미지 저장 도중 오류가 발생했습니다.");
            }
        }else{
            userMapper.updateProfile(user.getEmail(), imageUrl);
        }
    }

    @Override
    public void updatePassword(User user, Password password) {
        User findUser = userMapper.findByEmail(user.getEmail());
        if(findUser == null){
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }
        User updatedUser = User.builder()
                .userId(findUser.getUserId())
                .email(findUser.getEmail())
                .password(passwordEncoder.encode(password.getPassword()))
                .nickname(findUser.getNickname())
                .roleType(findUser.getRoleType())
                .build();

        userMapper.updatePassword(updatedUser);
    }

    @Override
    public void updateNickname(User user, Update update) {
        User findUser = userMapper.findByEmail(user.getEmail());
        if(findUser == null){
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }
        User updatedUser = User.builder()
                .userId(findUser.getUserId())
                .email(findUser.getEmail())
                .nickname(update.getNickname())
                .roleType(findUser.getRoleType())
                .build();

        userMapper.updateNickname(updatedUser);
    }

    @Override
    public User findByEmail(UserInfo userInfo) {
        return userMapper.findByEmail(userInfo.getEmail());
    }

    @Override
    public void delete(UserInfo userInfo){
        User user = userMapper.findByEmail(userInfo.getEmail());
        userMapper.delete(user);
    }

    @Bean
    public Function<UserDetails, User> fetchUser() {
        return userDetails -> userMapper.findByEmail(userDetails.getUsername());
    }

    @Override
    public boolean checkNickname(String nickname) {
        return userMapper.checkNickname(nickname);
    }

    @Override
    public void updatePassword(Password password) {
        User user = userMapper.findByEmail(password.getEmail());
        user.changePassword(passwordEncoder.encode(password.getPassword()));
        userMapper.updatePassword(user);
    }

    @Override
    public String resetPassword(Password password) {
        User user = userMapper.findByEmail(password.getEmail());
        System.out.println(password);
        if (user == null) {
            throw new IllegalArgumentException("해당 이메일을 사용하는 사용자가 없습니다.");
        }

        String newPassword = passwordService.generateRandomPassword();
        user.changePassword(passwordEncoder.encode(newPassword));
        userMapper.updatePassword(user);
        return newPassword;
    }
}
