package org.ens.requestservice.controller;

import org.ens.requestservice.entity.Mailing;
import org.ens.requestservice.service.MailingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emergency")
public class MailingController implements CrudController<Mailing> {

    @Autowired
    MailingService mailingService;

    @GetMapping("/mailings")
    @Override
    public List<Mailing> getAll() {
        return mailingService.getAll();
    }

    @GetMapping("/mailings/{id}")
    @Override
    public Mailing get(@PathVariable Long id) {
        return mailingService.get(id);
    }

    @PostMapping("/mailings")
    @Override
    public Mailing add(@RequestBody Mailing mailing) {
        mailingService.insert(mailing);
        return mailing;
    }

    @PutMapping("/mailings")
    @Override
    public Mailing update(@RequestBody Mailing mailing) {
        mailingService.insert(mailing);
        return mailing;
    }

    @DeleteMapping("/mailings/{id}")
    @Override
    public Long delete(@PathVariable Long id) {
        mailingService.delete(id);
        return id;
    }
}
