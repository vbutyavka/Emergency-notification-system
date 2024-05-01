package org.ens.requestservice.repository;

import org.ens.requestservice.entity.Sender;
import org.ens.requestservice.repository.crud.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SenderRepository extends CrudRepository<Sender> {
    Sender findByLogin(String login);

    Sender getByLogin(@Param("login")String login);
}
