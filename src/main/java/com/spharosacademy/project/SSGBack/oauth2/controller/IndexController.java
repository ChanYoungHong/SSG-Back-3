package com.spharosacademy.project.SSGBack.oauth2.controller;

import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import com.spharosacademy.project.SSGBack.oauth2.domain.SessionUser;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model){
        SessionUser user = (SessionUser) httpSession.getAttribute("userName");
        if(user != null){
            model.addAttribute("userName", user.getUserName());
        }
        return "naver2";
    }
}
