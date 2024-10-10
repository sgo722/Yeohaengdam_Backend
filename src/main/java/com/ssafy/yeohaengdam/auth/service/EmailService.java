package com.ssafy.yeohaengdam.auth.service;

import com.ssafy.yeohaengdam.auth.dto.AuthData;
import com.ssafy.yeohaengdam.utils.RedisService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Duration;

import static com.ssafy.yeohaengdam.auth.dto.AuthData.*;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final RedisService redisService;
    private static final String senderEmail = "sgo0418@gmail.com";
    private static int number;

    public static void createNumber() {
        number = (int) (Math.random() * (9000)) + 1000;
    }

    public MimeMessage createMail(String mail) {
        createNumber();
        redisService.setValues(mail,String.valueOf(number), Duration.ofMinutes(1));
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("[YeoHaengDam] 회원가입을 위한 이메일 인증");
            String body = "<h1>안녕하세요.</h1><h1>YeoHaengDam 입니다.</h1>"
                    + "<h3>회원가입을 위한 요청하신 인증 번호입니다.</h3><br>"
                    + "<h2>아래 코드를 회원가입 창으로 돌아가 입력해주세요.</h2>"
                    + "<div align='center' style='border:1px solid black; font-family:verdana;'>"
                    + "<h2>회원가입 인증 코드입니다.</h2>"
                    + "<h1 style='color:blue'>" + number + "</h1>"
                    + "</div><br>"
                    + "<h3>감사합니다.</h3>";
            message.setText(body, "UTF-8", "html");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return message;
    }

    public void sendEmail(String userId) {
        MimeMessage message = createMail(userId);
        javaMailSender.send(message);
    }

    public boolean verifyCode(Email email){
        String storedCode = redisService.getValues(email.getEmail());
        if(redisService.checkExistsValue(storedCode) && Integer.parseInt(storedCode) == email.getCode()){
            redisService.deleteValues(email.getEmail());
            return true;
        }
        return false;
    }
}
