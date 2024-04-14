package org.ens.requestservice.service;

import org.ens.requestservice.entity.Sender;
import org.ens.requestservice.repository.SenderRepository;
import org.ens.requestservice.service.crud.CrudService;
import org.springframework.stereotype.Service;

@Service
public class SenderService extends CrudService<Sender, SenderRepository> {
    public SenderService(SenderRepository repository) {
        super(repository);
    }
}
