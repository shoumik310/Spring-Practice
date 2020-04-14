package com.classpath.ordermgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OrderMgmtApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderMgmtApplication.class, args);
    }
}
