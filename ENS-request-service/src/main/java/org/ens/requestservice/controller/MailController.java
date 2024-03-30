package org.ens.requestservice.controller;

import org.ens.requestservice.entity.Mail;
import org.ens.requestservice.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emergency")
public class MailController implements CrudController<Mail> {

    @Autowired
    MailService mailService;

    @GetMapping("/mails")
    @Override
    public List<Mail> getAll() {
        return mailService.getAll();
    }

    @GetMapping("/mails/{id}")
    @Override
    public Mail get(@PathVariable Long id) {
        return mailService.get(id);
    }

    @PostMapping("/mails")
    @Override
    public Mail add(@RequestBody Mail mail) {
        mailService.insert(mail);
        return mail;
    }

    @PutMapping("/mails")
    @Override
    public Mail update(@RequestBody Mail mail) {
        mailService.insert(mail);
        return mail;
    }

    @DeleteMapping("/mails/{id}")
    @Override
    public Long delete(@PathVariable Long id) {
        mailService.delete(id);
        return id;
    }
}
