package org.ens.sending.service.repository;


import org.ens.sending.service.entity.MailHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailHistoryRepository extends JpaRepository<MailHistory, Long> {
}