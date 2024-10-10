package com.ssafy.yeohaengdam.user.service;

import com.ssafy.yeohaengdam.core.annotation.CurrentUser;
import com.ssafy.yeohaengdam.user.entity.User;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import static com.ssafy.yeohaengdam.user.dto.UserData.*;

public interface UserService {

    public void join(Join join);

    public void updatePassword(User user,Password password);

    public void updateNickname(User user,Update update);

    public void updateImage(@CurrentUser User user, @RequestPart MultipartFile image);

    public User findByEmail(UserInfo userInfo);

    public void delete(UserInfo userInfo);

    public boolean checkNickname(String nickname);

    public void updatePassword(Password password);

    public String resetPassword(Password password);
}
