package org.ens.requestservice.controller;

import org.ens.requestservice.entity.Mail;
import org.ens.requestservice.enums.MailStatus;
import org.ens.requestservice.exceptions.EmptyFieldException;
import org.ens.requestservice.exceptions.NonexistentFkException;
import org.ens.requestservice.service.MailService;
import org.ens.requestservice.service.MailingService;
import org.ens.requestservice.service.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/emergency")
public class MailController {

    @Autowired
    MailService mailService;
    @Autowired
    RecipientService recipientService;
    @Autowired
    MailingService mailingService;

    @GetMapping("/mails")
    public String getAll(Model model) {
        List<Mail> mails = mailService.getAll();
        model.addAttribute("mails", mails);
        return "mails";
    }

    @GetMapping("/mails/{id}")
    public String get(@PathVariable Long id, Model model) {
        Mail mail = mailService.get(id);
        model.addAttribute("mail", mail);
        return "edit-mail";
    }

    @PostMapping("/mails")
    public String add(@RequestParam String fkIdRecipient, @RequestParam String fkIdMailing, @RequestParam String status) {
        if (fkIdRecipient.isEmpty()) {
            throw new EmptyFieldException("Recipient ID");
        }
        if (fkIdMailing.isEmpty()) {
            throw new EmptyFieldException("Mailing ID");
        }
        if (status.isEmpty()) {
            throw new EmptyFieldException("Status");
        }
        if (recipientService.get(Long.valueOf(fkIdRecipient)) == null) {
            throw new NonexistentFkException("Recipient ID", fkIdRecipient, "Recipient");
        }
        if (mailingService.get(Long.valueOf(fkIdMailing)) == null) {
            throw new NonexistentFkException("Mailing ID", fkIdMailing, "Mailing");
        }
        Mail mail  = new Mail();
        mail.setFkIdRecipient(Long.valueOf(fkIdRecipient));
        mail.setFkIdMailing(Long.valueOf(fkIdMailing));
        mail.setStatus(MailStatus.valueOf(status));
        mailService.insert(mail);
        return "mails";
    }

    @PostMapping("/update-mail")
    public String update(@RequestParam Long id, @RequestParam String fkIdRecipient, @RequestParam String fkIdMailing, @RequestParam String status) {
        if (id == null) {
            throw new RuntimeException("Something went wrong: ID id null during update");
        }
        if (fkIdRecipient.isEmpty()) {
            throw new EmptyFieldException("Recipient ID");
        }
        if (fkIdMailing.isEmpty()) {
            throw new EmptyFieldException("Mailing ID");
        }
        if (status.isEmpty()) {
            throw new EmptyFieldException("Status");
        }
        if (recipientService.get(Long.valueOf(fkIdRecipient)) == null) {
            throw new NonexistentFkException("Recipient ID", fkIdRecipient, "Recipient");
        }
        if (mailingService.get(Long.valueOf(fkIdMailing)) == null) {
            throw new NonexistentFkException("Mailing ID", fkIdMailing, "Mailing");
        }
        Mail mail = new Mail();
        mail.setId(id);
        mail.setFkIdRecipient(Long.valueOf(fkIdRecipient));
        mail.setFkIdMailing(Long.valueOf(fkIdMailing));
        mail.setStatus(MailStatus.valueOf(status));
        mailService.insert(mail);
        return "mails";
    }

    @PostMapping("/delete-mail")
    public String delete(@RequestParam Long id, Model model) {
        mailService.delete(id);
        List<Mail> mails = mailService.getAll();
        model.addAttribute("mails", mails);
        return "mails";
    }
}
