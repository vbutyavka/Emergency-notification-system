package org.ens.sending.service.repository;

import org.ens.sending.service.entity.SmsJson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsJsonRepository extends JpaRepository<SmsJson, Long> {
}