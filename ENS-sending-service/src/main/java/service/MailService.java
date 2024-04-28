package service;

import entity.Mail;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.MailRepository;

@Service
public class MailService {

    @Autowired
    protected Logger log;

    protected final MailRepository repository;

    public MailService(MailRepository repository) {
        this.repository = repository;
    }

    public void insert(Mail entity) {
        log.info("Trying to insert()");
        repository.save(entity);
    }
}