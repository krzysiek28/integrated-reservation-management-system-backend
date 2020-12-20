package com.uliasz.irms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableWebSecurity
public class IrmsBackendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(IrmsBackendApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
    }
}
