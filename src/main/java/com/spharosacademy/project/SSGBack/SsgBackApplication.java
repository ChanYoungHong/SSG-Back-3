package com.spharosacademy.project.SSGBack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@SpringBootApplication(scanBasePackages = "com.spharosacademy")
@EnableJpaAuditing
public class SsgBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsgBackApplication.class, args);
    }

    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer customize() {
        return p -> {
            p.setOneIndexedParameters(true);    // 1부터 시작
            p.setMaxPageSize(20);                // size=10
        };
    }


}

