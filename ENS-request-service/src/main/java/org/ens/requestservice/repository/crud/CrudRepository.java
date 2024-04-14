package org.ens.requestservice.repository.crud;

import org.ens.requestservice.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CrudRepository<E extends AbstractEntity> extends JpaRepository<E, Long> {
}
