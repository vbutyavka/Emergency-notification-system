package org.ens.requestservice.service;

import org.ens.requestservice.entity.Mailing;
import org.ens.requestservice.repository.MailingRepository;
import org.ens.requestservice.service.crud.CrudService;
import org.springframework.stereotype.Service;

@Service
public class MailingService extends CrudService<Mailing, MailingRepository> {
    public MailingService(MailingRepository repository) {
        super(repository);
    }
}
