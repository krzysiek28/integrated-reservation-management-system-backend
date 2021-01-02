package com.uliasz.irms.internal.emailService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfiguration {

    @Value("${mail.host}") private String host;
    @Value("${mail.port}") private Integer port;
    @Value("${mail.username}") private String username;
    @Value("${mail.password}") private String password;
    @Value("${mail.transport.protocol}") private String protocol;
    @Value("${mail.properties.mail.smtp.auth}") private String smtpAuth;
    @Value("${mail.properties.mail.smtp.starttls.enable}") private String smtpStarttlsEnable;
    @Value("${mail.smtp.ssl.trust}") private String smtpSslTrust;
    @Value("${mail.encoding}") private String encoding;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        mailSender.setDefaultEncoding(encoding);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.auth", smtpAuth);
        props.put("mail.smtp.starttls.enable", smtpStarttlsEnable);
        props.put("mail.smtp.ssl.trust", smtpSslTrust);
        props.put("mail.debug", "true");

        return mailSender;
    }
}
