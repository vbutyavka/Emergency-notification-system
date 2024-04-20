package org.ens.requestservice.service;

import org.ens.requestservice.entity.LocalDistrict;
import org.ens.requestservice.repository.LocalDistrictRepository;
import org.ens.requestservice.service.crud.CrudService;
import org.springframework.stereotype.Service;

@Service
public class LocalDistrictService extends CrudService<LocalDistrict, LocalDistrictRepository> {
    public LocalDistrictService(LocalDistrictRepository repository) {
        super(repository);
    }
}
