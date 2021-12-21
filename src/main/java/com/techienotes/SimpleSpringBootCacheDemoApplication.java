package com.techienotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SimpleSpringBootCacheDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleSpringBootCacheDemoApplication.class, args);
    }
}
