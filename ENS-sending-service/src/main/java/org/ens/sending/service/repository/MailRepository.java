package org.ens.sending.service.repository;

import org.ens.sending.service.entity.Mail;
import org.ens.sending.service.enums.MailStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {

    @Transactional(readOnly = true)
    public List<Mail> findByStatus(MailStatus status);
}