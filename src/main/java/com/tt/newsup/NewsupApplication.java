package com.tt.newsup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NewsupApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsupApplication.class, args);
    }

}
