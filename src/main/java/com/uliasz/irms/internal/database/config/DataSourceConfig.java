package com.uliasz.irms.internal.database.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;
    @Value("${spring.datasource.data-username}")
    private String url;
    @Value("${spring.datasource.data-username}")
    private String userName;
    @Value("${spring.datasource.data-password}")
    private String password;


    public DataSourceConfig(
            @Value("${spring.datasource.driver-class-name}") String driverClass,
            @Value("${spring.datasource.data-username}") String url,
            @Value("${spring.datasource.data-username}") String userName,
            @Value("${spring.datasource.data-password}") String password) {
        this.driverClass = driverClass;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName(driverClass)
                .url(url)
                .username(userName)
                .password(password)
                .build();
    }
}
