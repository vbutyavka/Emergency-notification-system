package org.ens.sending.service.rabbitmq;

import org.ens.sending.service.entity.Mail;
import org.ens.sending.service.entity.MailHistory;
import org.ens.sending.service.entity.SmsJson;
import org.ens.sending.service.enums.MailStatus;
import org.ens.sending.service.service.MailHistoryService;
import org.ens.sending.service.service.MailService;
import org.ens.sending.service.smsru.SmsRuHttpClient;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CallbackListener {

    @Autowired
    private Logger log;

    @Autowired
    private MailService mailService;

    @Autowired
    private MailHistoryService mailHistoryService;

    @RabbitListener(queues = "${rabbitmq.callback}")
    public void getMessageFromQueue (@Payload SmsJson smsJson) {
        UUID uuid = UUID.randomUUID();

        Mail mail = null;
        MailHistory history = new MailHistory();

        try {
            mail = mailService.get(smsJson.getMailId());
            history.setIdMailing(mail.getFkIdMailing());
            history.setRecipientPhone(smsJson.getAddress());
            mailService.delete(smsJson.getMailId());

            int responseCode = SmsRuHttpClient.makePostRequest(uuid, smsJson);

            if (responseCode == 204 || responseCode == 200) {//TODO: костыль - при получении лицензии со стороны SMS.ru убрать данный юлок кода
                responseCode = 100;
            }

            if (responseCode > 99 && responseCode < 104) {
                history.setStatus(MailStatus.SENT);
            } else {
                history.setStatus(MailStatus.FAILED_AFTER_CALLBACK);
            }
            mailHistoryService.insert(history);

        } catch (Exception e) {
            log.info("Message for recipient {} could not be converted from JSON and wasn't sent", smsJson.getAddress());
            history.setStatus(MailStatus.FAILED);
            mailHistoryService.insert(history);
        }
    }
}
