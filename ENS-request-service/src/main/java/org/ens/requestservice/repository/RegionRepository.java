package org.ens.requestservice.repository;

import org.ens.requestservice.entity.Region;
import org.ens.requestservice.repository.crud.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends CrudRepository<Region> {
}
