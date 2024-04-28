package org.ens.sending.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.ens.sending.service.repository")
public class EnsSendingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnsSendingServiceApplication.class, args);
    }
}
