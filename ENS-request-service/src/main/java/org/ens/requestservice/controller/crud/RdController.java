package org.ens.requestservice.controller.crud;

import org.ens.requestservice.entity.AbstractEntity;
import org.ens.requestservice.service.crud.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class RdController<E extends AbstractEntity, S extends ICrudService<E>> implements IRdController<E> {

    private final S service;

    @Autowired
    protected RdController(S service) {
        this.service = service;
    }
}
