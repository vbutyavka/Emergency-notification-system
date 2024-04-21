package org.ens.requestservice.repository;

import org.ens.requestservice.entity.Recipient;
import org.ens.requestservice.repository.crud.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipientRepository extends CrudRepository<Recipient> {

    @Query("SELECT r FROM Recipient r WHERE r.id > :from AND r.id <= :to")
    List<Recipient> getBatch(@Param("from") Long from, @Param("to") Long to);

    @Query("SELECT r FROM Recipient r WHERE r.phoneNumber LIKE :phoneNumber")
    Recipient getByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
