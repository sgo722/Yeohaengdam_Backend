package com.ssafy.yeohaengdam.auth.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class AuthData {

    @Data
    public static class Email{
        @NotNull(message = "이메일을 입력해주세요")
        private String email;
        private int code;
    }
}
