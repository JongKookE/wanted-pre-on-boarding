package com.example.wantedpreonboarding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WantedPreOnBoardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(WantedPreOnBoardingApplication.class, args);
    }

}
