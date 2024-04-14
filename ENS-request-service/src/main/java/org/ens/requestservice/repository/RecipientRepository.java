package org.ens.requestservice.repository;

import org.ens.requestservice.entity.Recipient;
import org.ens.requestservice.repository.crud.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipientRepository extends CrudRepository<Recipient> {
}
