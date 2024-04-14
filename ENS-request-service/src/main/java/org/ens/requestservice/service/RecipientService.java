package org.ens.requestservice.service;

import org.ens.requestservice.entity.Recipient;
import org.ens.requestservice.repository.RecipientRepository;
import org.ens.requestservice.service.crud.CrudService;
import org.springframework.stereotype.Service;

@Service
public class RecipientService extends CrudService<Recipient, RecipientRepository> {
    public RecipientService(RecipientRepository repository) {
        super(repository);
    }
}
