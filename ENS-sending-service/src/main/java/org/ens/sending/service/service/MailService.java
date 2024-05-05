package org.ens.sending.service.service;

import org.ens.sending.service.entity.Mail;
import org.ens.sending.service.repository.MailRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Mail get(Long id) {
        log.info("Trying to get({})", id);
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        log.info("Trying to delete({})", id);
        repository.deleteById(id);
    }
}