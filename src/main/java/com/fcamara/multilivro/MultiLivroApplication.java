package com.fcamara.multilivro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableWebMvc
public class MultiLivroApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiLivroApplication.class, args);
    }

}
