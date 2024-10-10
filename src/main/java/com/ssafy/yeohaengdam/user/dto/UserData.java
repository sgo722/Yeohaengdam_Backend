package com.ssafy.yeohaengdam.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

public class UserData {

    @Data
    public static class Join{
        private String email;


        @NotBlank(message = "닉네임을 입력해주세요")
        private String nickname;

        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;
    }

    @Data
    public static class Update{
        private String email;

        @NotBlank(message = "닉네임을 입력해주세요")
        private String nickname;
    }

    @Data
    public static class Password{

        private String email;

        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;
    }

    @Data
    public static class UserInfo{
        private String email;
        private String password;
        private String profileImage;
        private String nickname;
    }
}
