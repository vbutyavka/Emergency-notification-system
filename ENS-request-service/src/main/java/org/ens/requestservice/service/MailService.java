package org.ens.requestservice.service;

import org.ens.requestservice.entity.Mail;
import org.ens.requestservice.repository.MailRepository;
import org.ens.requestservice.service.crud.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailService extends CrudService<Mail, MailRepository> {
    @Autowired
    public MailService(MailRepository mailRepository) {
        super.setRepository(mailRepository);
    }
}