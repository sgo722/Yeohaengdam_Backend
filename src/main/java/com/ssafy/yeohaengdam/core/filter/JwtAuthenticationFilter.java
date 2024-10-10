package com.ssafy.yeohaengdam.core.filter;

import com.ssafy.yeohaengdam.auth.service.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public final String AUTHORIZATION_HEAD = "Authorization";

    // JwtTokenService는 토큰에 대해 유효성 검사를 하거나 토큰을 생성하기 위한 객체다.
    private final JwtTokenService jwtTokenService;


//    private final RedisTemplate<String, String> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 요청 객체에 헤더를 확인 하여 토큰 정보가 있는지 확인한다.
        String accessToken = resolveToken(request);
        System.out.println("accessToken = " + accessToken);

        try{

            // 토큰 정보가 존재하고, 유효한 토큰인지 확인한다.
            if(StringUtils.hasText(accessToken) && jwtTokenService.validateToken(accessToken)){
                // 유효한 토큰이라면 ? 토큰에 대한 정보를 가지고 인증 객체를 생성하여 SecurityContextHolder에 Authentication 객체를 저장해야한다.
                Authentication authentication = jwtTokenService.parseAuthentication(accessToken);
                System.out.println(authentication);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch(Exception e){
            SecurityContextHolder.clearContext();
            e.printStackTrace();
        }
        doFilter(request, response, filterChain);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEAD);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }
}
