package com.spharosacademy.project.SSGBack.sms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spharosacademy.project.SSGBack.sms.dto.request.Request;
import com.spharosacademy.project.SSGBack.sms.dto.response.SmsResponse;
import com.spharosacademy.project.SSGBack.sms.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
public class SmsController {
    private final SmsService smsService;

    @PostMapping("/users/sms")
    public ResponseEntity<SmsResponse> test(@RequestBody Request request) throws UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException, InvalidKeyException, JsonProcessingException {
        SmsResponse data = smsService.sendSms(request.getRecipientPhoneNumber(), request.getContent());
        return ResponseEntity.ok().body(data);
    }

}
