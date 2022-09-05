package com.spharosacademy.project.SSGBack;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spharosacademy.project.SSGBack.sns.service.SmsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

@SpringBootTest
public class ApplicationTests {

    @Autowired
    private SmsService smsService;

    @Test
    void sendSms() throws JsonProcessingException, ParseException, UnsupportedEncodingException, URISyntaxException,
            NoSuchAlgorithmException, InvalidKeyException {
        smsService.sendSms("01036699883", "하이");
    }
}
