package com.t3h;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.t3h")
@EntityScan(basePackages="com.t3h.entity")
public class UniqloSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniqloSpringbootApplication.class, args);
    }

}
