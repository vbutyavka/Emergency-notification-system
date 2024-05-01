package org.ens.requestservice.service;

import org.ens.requestservice.entity.Recipient;
import org.ens.requestservice.repository.RecipientRepository;
import org.ens.requestservice.service.crud.CrudService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipientService extends CrudService<Recipient, RecipientRepository> {

    private static final int BATCH_SIZE = 50;

    public RecipientService(RecipientRepository repository) {
        super(repository);
    }

    public List<Recipient> getBatch(Long from) {
        log.info("Trying to getBatch({})", from);
        return repository.getBatch(from, from + BATCH_SIZE);
    }

    public Recipient getByPhoneNumber(String phoneNumber) {
        log.info("Trying to getByPhoneNumber({})", phoneNumber);
        return repository.getByPhoneNumber(phoneNumber);
    }

    public List<Recipient> getAllByFederalDistrict(Long federalDistrictId) {
        log.info("Trying to getAllByFederalDistrict({})", federalDistrictId);
        List<Recipient> recipients = new ArrayList<>();
        Long from = repository.getMinId();
        Long to = repository.getMaxId();
        for (Long i = from; i < to + BATCH_SIZE; i += BATCH_SIZE) {
            recipients.addAll(repository.getBatchByFederalDistrict(federalDistrictId, i, i + BATCH_SIZE - 1));
        }
        return recipients;
    }

    public List<Recipient> getAllByRegion(Long regionId) {
        log.info("Trying to getAllByRegion({})", regionId);
        List<Recipient> recipients = new ArrayList<>();
        Long from = repository.getMinId();
        Long to = repository.getMaxId();
        for (Long i = from; i < to + BATCH_SIZE; i += BATCH_SIZE ) {
            recipients.addAll(repository.getBatchByRegion(regionId, i, i + BATCH_SIZE - 1));
        }
        return recipients;
    }

    public List<Recipient> getAllByLocalDistrict(Long localDistrictId) {
        log.info("Trying to getAllByLocalDistrict({})", localDistrictId);
        List<Recipient> recipients = new ArrayList<>();
        Long from = repository.getMinId();
        Long to = repository.getMaxId();
        for (Long i = from; i < to + BATCH_SIZE; i += BATCH_SIZE) {
            recipients.addAll(repository.getBatchByLocalDistrict(localDistrictId, i, i + BATCH_SIZE - 1));
        }
        return recipients;
    }
}