package com.spharosacademy.project.SSGBack.security.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Configuration
@Log4j2
@RequestMapping("/sample/")
public class SecurityLoginController {

    @GetMapping("/all")
    public void loginAll(){
        log.info("exAll..................");
    }

    @GetMapping("/user")
    public void loginMember(){
        log.info("exMember...................");
    }

    @GetMapping("/manager")
    public void lognManager(){
        log.info("exManager.....................");
    }

    @GetMapping("/admin")
    public void loginAdmin() {
        log.info("exAdmin........................");
    }


}
