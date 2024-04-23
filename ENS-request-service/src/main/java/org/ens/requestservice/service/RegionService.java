package org.ens.requestservice.service;

import org.ens.requestservice.entity.Region;
import org.ens.requestservice.repository.RegionRepository;
import org.ens.requestservice.service.crud.CrudService;
import org.springframework.stereotype.Service;

@Service
public class RegionService extends CrudService<Region, RegionRepository> {
    public RegionService(RegionRepository repository) {
        super(repository);
    }
}
