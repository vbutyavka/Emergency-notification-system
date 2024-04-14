package org.ens.requestservice.service.crud;

import org.ens.requestservice.entity.AbstractEntity;
import org.ens.requestservice.repository.crud.CrudRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class CrudService<E extends AbstractEntity, R  extends CrudRepository<E>> implements ICrudService<E> {

    @Autowired
    private Logger log;

    protected final R repository;

    @Autowired
    public CrudService(R repository) {
        this.repository = repository;
    }

    @Override
    public List<E> getAll() {
        log.info("Trying to getAll()");
        return repository.findAll();
    }

    @Override
    public E get(Long id) {
        E entity = null;
        log.info("Trying to get() with id={}", id);
        Optional<E> optionalEntity = repository.findById(id);
        if(optionalEntity.isPresent()) {
            entity = optionalEntity.get();
        }
        return entity;
    }

    @Override
    public void insert(E entity) {
        log.info("Trying to insert()");
        repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        log.info("Trying to delete() with id={}", id);
        repository.deleteById(id);
    }
}
