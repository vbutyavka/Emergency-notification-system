package org.ens.requestservice.repository;

import org.ens.requestservice.entity.Mailing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailingRepository extends JpaRepository<Mailing, Long> {
}
