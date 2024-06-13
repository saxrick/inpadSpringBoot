package com.inpad.spring.inpadspringboot.configuration;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://localhost:5432/inpaddb")
//                .url("jdbc:postgresql://rc1a-ugphrymfc28fdzkb.mdb.yandexcloud.net:6432/inpaddb?targetServerType=master&ssl=false")
                .username("artem")
                .password("artem123")
                .build();
    }
}

