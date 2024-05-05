package org.ens.sending.service.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ens.sending.service.entity.Mail;
import org.ens.sending.service.entity.MailHistory;
import org.ens.sending.service.entity.SmsJson;
import org.ens.sending.service.enums.MailStatus;
import org.ens.sending.service.service.MailHistoryService;
import org.ens.sending.service.service.MailService;
import org.ens.sending.service.smsru.SmsRuHttpClient;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RabbitMqListener {

    @Autowired
    private Logger log;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MailService mailService;

    @Autowired
    private MailHistoryService mailHistoryService;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void getMessageFromQueue (String message) {
        UUID uuid = UUID.randomUUID();

        ObjectMapper mapper = new ObjectMapper();
        SmsJson smsJson = new SmsJson();
        Mail mail = mailService.get(smsJson.getMailId());
        try {
            smsJson = mapper.readValue(message, SmsJson.class);

            int responseCode = SmsRuHttpClient.makePostRequest(uuid, smsJson);

            if (responseCode == 204) {//TODO: костыль - при получении лицензии со стороны SMS.ru убрать данный юлок кода
                responseCode = 100;
            }

            //TODO для теста
            if (smsJson.getAddress().contains("123")) {
                responseCode = 400;
            } else if (smsJson.getAddress().contains("456")) {
                throw new Exception("for test exception");
            }
            //TODO для теста

            if (responseCode > 99 && responseCode < 104) {
                mailService.delete(smsJson.getMailId());

                MailHistory history = new MailHistory();
                history.setIdMailing(mail.getFkIdMailing());
                history.setRecipientPhone(smsJson.getAddress());
                history.setStatus(MailStatus.SENT);
                mailHistoryService.insert(history);
            } else {
                mail.setStatus(MailStatus.CALLBACK);
                mailService.insert(mail);
                rabbitTemplate.convertAndSend(smsJson.getAsJson());
            }

        } catch (Exception e) {
            log.info("Message for recipient {} could not be converted from JSON and wasn't sent", smsJson.getAddress());
            MailHistory history = new MailHistory();
            history.setIdMailing(mail.getFkIdMailing());
            history.setRecipientPhone(smsJson.getAddress());
            history.setStatus(MailStatus.FAILED);
            mailHistoryService.insert(history);
        }
    }
}