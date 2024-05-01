package org.ens.sending.service.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ens.sending.service.entity.SmsJson;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqListener {

    @Autowired
    protected Logger log;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void getMessageFromQueue (String message) {
        ObjectMapper mapper = new ObjectMapper();
        SmsJson smsJson = new SmsJson();
        try {
            smsJson = mapper.readValue(message, SmsJson.class);
            System.out.println("Received message: " + smsJson);
        } catch (Exception e) {
            log.info("Message for recipient {} could not be converted from JSON and wasn't sent", smsJson.getAddress());
        }
    }
}