package org.ens.requestservice.service;

import org.ens.requestservice.entity.Mail;
import org.ens.requestservice.repository.MailRepository;
import org.ens.requestservice.service.crud.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService extends CrudService<Mail, MailRepository> {
    public MailService(MailRepository repository) {
        super(repository);
    }

    public void insertAll(List<Mail> mails) {
        repository.saveAll(mails);
    }
}