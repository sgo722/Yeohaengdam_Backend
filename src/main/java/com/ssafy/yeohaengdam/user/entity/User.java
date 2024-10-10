package com.ssafy.yeohaengdam.user.entity;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class User {




    private int userId;

    private String oauthId;

    private String email;

    private String nickname;

    private String password;

    private String profileImage;

    private RoleType roleType;

    private String type;

    public User(String oauthId, String email, String type, String nickname){
        this.oauthId = oauthId;
        this.password = "password";
        this.nickname = nickname;
        this.email = email;
        this.type = type;
        this.roleType = RoleType.USER;

    }


    public void changePassword(String password) {
        this.password = password;
    }
}
