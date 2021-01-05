package com.uliasz.irms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableWebSecurity
@EnableScheduling
public class IrmsBackendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(IrmsBackendApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
    }
}
