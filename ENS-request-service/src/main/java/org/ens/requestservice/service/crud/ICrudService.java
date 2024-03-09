package org.ens.requestservice.service.crud;

import java.util.List;

public interface ICrudService<E> {

    public List<E> getAll();

    public E get(Long id);

    public void insert(E entity);

    public void delete(Long id);
}
