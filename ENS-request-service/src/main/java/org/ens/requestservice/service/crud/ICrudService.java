package org.ens.requestservice.service.crud;

import org.ens.requestservice.entity.AbstractEntity;

import java.util.List;

public interface ICrudService<E extends AbstractEntity> {

    public List<E> getAll();

    public E get(Long id);

    public E insert(E entity);

    public void delete(Long id);
}
