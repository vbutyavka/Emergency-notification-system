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
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${rabbitmq.callback}")
    private String queueCallback;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void getMessageFromQueue (String message) {
        UUID uuid = UUID.randomUUID();

        ObjectMapper mapper = new ObjectMapper();
        SmsJson smsJson = new SmsJson();
        Mail mail = null;
        try {
            smsJson = mapper.readValue(message, SmsJson.class);
            mail = mailService.get(smsJson.getMailId());
            if (mail == null) {
                throw new NullPointerException("[" + uuid + "] Mail is null after get by id = " + smsJson.getMailId());
            }

            int responseCode = SmsRuHttpClient.makePostRequest(uuid, smsJson);

            if (responseCode == 204 || responseCode == 200) {//TODO: костыль - при получении лицензии со стороны SMS.ru убрать данный юлок кода
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

//                Map<String, Object> jsonMap = new HashMap<>();
//                jsonMap.put("mail_id", smsJson.getMailId());
//                jsonMap.put("address", smsJson.getAddress());
//                jsonMap.put("text", smsJson.getText());
//                String jsonMail = mapper.writeValueAsString(jsonMap);
                rabbitTemplate.convertAndSend(queueCallback, smsJson);
            }

        } catch (NullPointerException e) {
            log.info(e.getMessage());
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