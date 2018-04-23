package com.issoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.issoft.service")
@SpringBootApplication
public class JollyRogerApplication {
    public static void main(String[] args) {
        SpringApplication.run(JollyRogerApplication.class, args);
    }
}
