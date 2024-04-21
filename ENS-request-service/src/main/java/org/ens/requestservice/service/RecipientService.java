package org.ens.requestservice.service;

import org.ens.requestservice.entity.Recipient;
import org.ens.requestservice.repository.RecipientRepository;
import org.ens.requestservice.service.crud.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipientService extends CrudService<Recipient, RecipientRepository> {

    private static final int BATCH_SIZE = 50;

    public RecipientService(RecipientRepository repository) {
        super(repository);
    }

    public List<Recipient> getBatch(Long from) {
        log.info("Trying to getBatch(" + from + ")");
        return repository.getBatch(from, from + BATCH_SIZE);
    }

    public Recipient getByPhoneNumber(String phoneNumber) {
        log.info("Trying to getByPhoneNumber(" + phoneNumber + ")");
        return repository.getByPhoneNumber(phoneNumber);
    }
}