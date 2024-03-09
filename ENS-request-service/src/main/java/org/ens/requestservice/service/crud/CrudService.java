package org.ens.requestservice.service.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class CrudService<E, R  extends JpaRepository<E, Long>> implements ICrudService<E> {

    private R repository;

    @Override
    public List<E> getAll() {
        return repository.findAll();
    }

    @Override
    public E get(Long id) {
        E entity = null;
        Optional<E> optionalEntity = repository.findById(id);
        if(optionalEntity.isPresent()) {
            entity = optionalEntity.get();
        }
        return entity;
    }

    @Override
    public void insert(E entity) {
        repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
