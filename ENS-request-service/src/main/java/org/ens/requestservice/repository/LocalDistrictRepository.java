package org.ens.requestservice.repository;

import org.ens.requestservice.entity.LocalDistrict;
import org.ens.requestservice.repository.crud.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalDistrictRepository extends CrudRepository<LocalDistrict> {
}
