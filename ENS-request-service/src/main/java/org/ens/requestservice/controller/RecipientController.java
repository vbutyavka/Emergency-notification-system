package org.ens.requestservice.controller;

import org.ens.requestservice.entity.Recipient;
import org.ens.requestservice.service.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emergency")
public class RecipientController implements CrudController<Recipient> {
    
    @Autowired
    RecipientService recipientService;

    @GetMapping("/recipients")
    @Override
    public List<Recipient> getAll() {
        return recipientService.getAll();
    }

    @GetMapping("/recipients/{id}")
    @Override
    public Recipient get(@PathVariable Long id) {
        return recipientService.get(id);
    }

    @PostMapping("/recipients")
    @Override
    public Recipient add(@RequestBody Recipient recipient) {
        recipientService.insert(recipient);
        return recipient;
    }

    @PutMapping("/recipients")
    @Override
    public Recipient update(@RequestBody Recipient recipient) {
        recipientService.insert(recipient);
        return recipient;
    }

    @DeleteMapping("/recipients/{id}")
    @Override
    public Long delete(@PathVariable Long id) {
        recipientService.delete(id);
        return id;
    }
}
