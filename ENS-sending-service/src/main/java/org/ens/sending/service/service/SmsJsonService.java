package org.ens.sending.service.service;

import org.ens.sending.service.entity.SmsJson;
import org.ens.sending.service.repository.SmsJsonRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsJsonService {

    @Autowired
    protected Logger log;

    protected final SmsJsonRepository repository;

    public SmsJsonService(SmsJsonRepository repository) {
        this.repository = repository;
    }

    public void insert(SmsJson entity) {
        log.info("Trying to insert()");
        repository.save(entity);
    }

    public SmsJson get(Long id) {
        log.info("Trying to get({})", id);
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        log.info("Trying to delete({})", id);
        repository.deleteById(id);
    }

    public List<SmsJson> getAll() {
        log.info("Trying to getAll()");
        return repository.findAll();
    }
}