package org.ens.requestservice.repository;

import org.ens.requestservice.entity.Sender;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SenderRepository extends JpaRepository<Sender, Long> {
}
