package org.ens.requestservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CrudController<E> {

    public List<E> getAll();

    public E get(@PathVariable Long id);

    public E add(@RequestBody E entity);

    public E update(@RequestBody E entity);

    public Long delete(@PathVariable Long id);
}
