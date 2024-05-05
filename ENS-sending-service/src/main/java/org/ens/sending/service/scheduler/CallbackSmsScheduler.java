package org.ens.sending.service.scheduler;


import org.ens.sending.service.entity.SmsJson;
import org.ens.sending.service.service.SmsJsonService;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CallbackSmsScheduler {

    @Autowired
    SmsJsonService smsJsonService;

    @Value("${rabbitmq.callback}")
    private String queueCallback;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Logger log;

    @Scheduled(fixedRate = 300000)
    public void execute() {
        UUID uuid = UUID.randomUUID();
        log.info("[{}] Starting CallbackSmsScheduler", uuid);
        List<SmsJson> smsJsons = smsJsonService.getAll();
        log.info("[{}] Found {} messages to resend", uuid, smsJsons.size());
        smsJsons.forEach(smsJson -> {
            smsJsonService.delete(smsJson.getId());
            rabbitTemplate.convertAndSend(queueCallback, smsJson);
        });
        log.info("[{}] Finishing CallbackSmsScheduler", uuid);
    }
}
