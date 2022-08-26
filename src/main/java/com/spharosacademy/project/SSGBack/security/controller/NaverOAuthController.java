package com.spharosacademy.project.SSGBack.security.controller;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import api.NaverLoginApi;
import com.github.scribejava.core.oauth.OAuth20Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

@Slf4j
@RestController
@RequestMapping("/login")
public class NaverOAuthController {

    @GetMapping("/naver")
    public String naverConnect() {

        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString(32);

        StringBuffer url = new StringBuffer();
        url.append("https://nid.naver.com/oauth2.0/authorize");
        url.append("client_id=" + "hvDqUA3VYSNUesE1784z");
        url.append("authorization_code");
        url.append("redirect_uri=http://localhost:8080/login/naver");
        url.append("state" + state);

        return "redirect:" + url;

    }



}
