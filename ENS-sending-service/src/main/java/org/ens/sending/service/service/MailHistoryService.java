package org.ens.sending.service.service;

import org.ens.sending.service.entity.MailHistory;
import org.ens.sending.service.repository.MailHistoryRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailHistoryService {

    @Autowired
    protected Logger log;

    protected final MailHistoryRepository repository;

    public MailHistoryService(MailHistoryRepository repository) {
        this.repository = repository;
    }

    public void insert(MailHistory entity) {
        log.info("Trying to insert()");
        repository.save(entity);
    }
}