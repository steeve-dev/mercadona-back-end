package com.example.mercadonabackend;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class MercadonaBackEndApplication {
    public static void main(String[] args){
        System.out.println(System.getenv("DATABASE_USERNAME"));
        SpringApplication.run(MercadonaBackEndApplication.class, args);
    }
}
