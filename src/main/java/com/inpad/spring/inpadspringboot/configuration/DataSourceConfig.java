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

//public class DataSourceConfig {
//    public static void main(String[] args) {
//        String DB_URL     = "jdbc:postgresql://rc1a-u20bpg2idyn19l5i.mdb.yandexcloud.net:6432/inpaddb?targetServerType=master&ssl=true&sslmode=verify-full";
//        String DB_USER    = "artem";
//        String DB_PASS    = "Artem12378!!";
//
//        try {
//            Class.forName("org.postgresql.Driver");
//
//            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
//            ResultSet q = conn.createStatement().executeQuery("SELECT version()");
//            if(q.next()) {System.out.println(q.getString(1));}
//
//            conn.close();
//        }
//        catch(Exception ex) {ex.printStackTrace();}
//    }
//}
