package org.ens.requestservice.controller.crud;

import org.ens.requestservice.entity.AbstractEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface IRdController<E extends AbstractEntity> {

    public String getAll(Model model);

    public String get(@PathVariable Long id, Model model);

    public String delete(@RequestParam Long id, Model model);
}
