package com.example.cling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CLingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CLingApplication.class, args);

    }

}
