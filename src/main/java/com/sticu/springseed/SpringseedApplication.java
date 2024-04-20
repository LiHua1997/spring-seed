package com.sticu.springseed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringseedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringseedApplication.class, args);
    }
}
