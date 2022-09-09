package com.spharosacademy.project.SSGBack.sms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spharosacademy.project.SSGBack.sms.service.SmsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

//@SpringBootTest
//class SmsControllerTest {
//
//    @Autowired
//    private SmsService smsService;
//
//    @Test
//    void sendSms() throws JsonProcessingException, ParseException, UnsupportedEncodingException, URISyntaxException,
//            NoSuchAlgorithmException, InvalidKeyException {
//        smsService.sendSms("01036699883", "[네이버 문자인증]\n" +
//                "인증번호 "+"[602554]" + "를 입력해주세요");
//    }
//
//}