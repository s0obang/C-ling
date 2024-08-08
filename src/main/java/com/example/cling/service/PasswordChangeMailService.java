package com.example.cling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
public class PasswordChangeMailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private RedisUtil redisUtil;

    private String generateAuthNumber() {
        Random r = new Random();
        StringBuilder randomNumber = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            randomNumber.append(r.nextInt(10));
        }
        return randomNumber.toString();
    }

    public String sendPasswordChangeAuth(String email) {
        String authNumber = generateAuthNumber();
        String setFrom = "quffl2002@gmail.com";
        String toMail = email;
        String title = "[크링] 비밀번호 변경 인증 이메일입니다.";
        String content = "크링을 방문해주셔서 감사합니다.<br><br>" +
                "인증 번호는 " + authNumber + "입니다.<br>" +
                "인증번호를 제대로 입력해주세요.";

        mailSend(setFrom, toMail, title, content);

        // 인증번호와 이메일을 Redis에 저장, 5분 유효
        redisUtil.setDataExpire(authNumber, email, 60 * 5L);

        return authNumber;
    }

    private void mailSend(String setFrom, String toMail, String title, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String getEmailByAuthNum(String authNum) {
        return redisUtil.getData(authNum);
    }
}
