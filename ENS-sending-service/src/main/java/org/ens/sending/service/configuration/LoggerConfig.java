package org.ens.sending.service.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LoggerConfig {
    @Bean
    public Logger getLogger() {
        return LoggerFactory.getLogger("loggerBean");
    }
}
