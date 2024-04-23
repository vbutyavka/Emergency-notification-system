package org.ens.requestservice.service;

import org.ens.requestservice.entity.FederalDistrict;
import org.ens.requestservice.repository.FederalDistrictRepository;
import org.ens.requestservice.service.crud.CrudService;
import org.springframework.stereotype.Service;

@Service
public class FederalDistrictService extends CrudService<FederalDistrict, FederalDistrictRepository> {
    public FederalDistrictService(FederalDistrictRepository repository) {
        super(repository);
    }
}
