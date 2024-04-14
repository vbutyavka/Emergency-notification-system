package org.ens.requestservice.service.crud;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class CrudService<E, R  extends JpaRepository<E, Long>> implements ICrudService<E> {

    private E entityLog;
    private R repository;

    @Autowired
    private Logger log;

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

    protected void setRepository(R repository) {
        this.repository = repository;
    }
}
