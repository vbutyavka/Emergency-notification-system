package org.ens.requestservice.repository;

import org.ens.requestservice.entity.Mail;
import org.ens.requestservice.repository.crud.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends CrudRepository<Mail> {
}
