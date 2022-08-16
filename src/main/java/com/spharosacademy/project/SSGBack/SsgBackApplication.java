package com.spharosacademy.project.SSGBack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = "com.spharosacademy")
@EnableJpaAuditing
public class SsgBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsgBackApplication.class, args);
    }   



}

