package org.ens.requestservice.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class LoggerConfiguration {

    @Bean
    public Logger getLogger() {
        return LoggerFactory.getLogger("loggerBean");
    }
}
