package org.ens.requestservice.repository;

import org.ens.requestservice.entity.FederalDistrict;
import org.ens.requestservice.repository.crud.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FederalDistrictRepository extends CrudRepository<FederalDistrict> {
}
