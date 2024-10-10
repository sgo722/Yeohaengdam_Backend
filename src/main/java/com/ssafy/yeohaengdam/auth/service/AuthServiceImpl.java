package com.ssafy.yeohaengdam.auth.service;

import com.ssafy.yeohaengdam.auth.dto.JwtToken;
import com.ssafy.yeohaengdam.auth.dto.LoginRequest;
import com.ssafy.yeohaengdam.user.entity.User;
import com.ssafy.yeohaengdam.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public JwtToken login(LoginRequest loginRequest) {
        System.out.println(loginRequest);
        User user = userMapper.findByEmail(loginRequest.getEmail());
        System.out.println(user);

        if(!loginRequest.getPassword().equals(user.getPassword())){
            throw new IllegalArgumentException("비밀번호를 확인하세요.");
        }

        return jwtTokenService.generateToken(user);
    }

    @Override
    public JwtToken refresh(String refreshToken) {
        String email = jwtTokenService.getUsername(refreshToken);
        if(email == null) throw new IllegalArgumentException("유효하지 않은 토큰 입니다.");

        return jwtTokenService.generateTokenByRefreshToken(refreshToken);
    }
}
