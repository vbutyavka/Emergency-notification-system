package org.ens.requestservice.repository;

import org.ens.requestservice.entity.Mailing;
import org.ens.requestservice.repository.crud.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailingRepository extends CrudRepository<Mailing> {
}
