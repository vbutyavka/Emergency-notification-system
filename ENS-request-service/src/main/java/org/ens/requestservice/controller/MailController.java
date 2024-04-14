package org.ens.requestservice.controller;

import org.ens.requestservice.entity.Mail;
import org.ens.requestservice.enums.MailStatus;
import org.ens.requestservice.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/emergency")
public class MailController /*implements CrudController<Mail>*/ {

    @Autowired
    MailService mailService;

    @GetMapping("/mails")
    public String getAll(Model model) {
        List<Mail> mails = mailService.getAll();
        model.addAttribute("mails", mails);
        return "mails";
    }

    @GetMapping("/mails/{id}")
    public Mail get(@PathVariable Long id) {
        return mailService.get(id);
    }

    @PostMapping("/mails")
    public String add(@RequestParam String fkIdRecipient, @RequestParam String fkIdMailing, @RequestParam String status) {
        Mail mail  = new Mail();
        mail.setFkIdRecipient(Long.valueOf(fkIdRecipient));
        mail.setFkIdMailing(Long.valueOf(fkIdMailing));
        mail.setStatus(MailStatus.valueOf(status));
        mailService.insert(mail);
        return "mails";
    }

    @PutMapping("/mails")
    public Mail update(@RequestBody Mail mail) {
        mailService.insert(mail);
        return mail;
    }

    @DeleteMapping("/mails/{id}")
    public Long delete(@PathVariable Long id) {
        mailService.delete(id);
        return id;
    }
}
