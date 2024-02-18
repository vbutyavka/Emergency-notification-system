package org.ens.requestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EnsRequestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnsRequestServiceApplication.class, args);
    }

}
