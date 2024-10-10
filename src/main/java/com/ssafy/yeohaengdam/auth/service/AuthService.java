package com.ssafy.yeohaengdam.auth.service;


import com.ssafy.yeohaengdam.auth.dto.JwtToken;
import com.ssafy.yeohaengdam.auth.dto.LoginRequest;

public interface AuthService {
    JwtToken login(LoginRequest loginRequest);

    JwtToken refresh(String refreshToken);


}
