package org.ens.requestservice.controller;

import org.ens.requestservice.entity.Mailing;
import org.ens.requestservice.exceptions.EmptyFieldException;
import org.ens.requestservice.exceptions.NonexistentFkException;
import org.ens.requestservice.service.MailingService;
import org.ens.requestservice.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/emergency")
public class MailingController {

    @Autowired
    MailingService mailingService;

    @Autowired
    SenderService senderService;

    @GetMapping("/mailings")
    public String getAll(Model model) {
        List<Mailing> mailings = mailingService.getAll();
        model.addAttribute("mailings", mailings);
        return "mailings";
    }

    @GetMapping("/mailings/{id}")
    public String get(@PathVariable Long id, Model model) {
        Mailing mailing = mailingService.get(id);
        model.addAttribute("mailing", mailing);
        return "edit-mailing";
    }

    @PostMapping("/mailings")
    public String add(@RequestParam String text, @RequestParam String fkIdSender) {
        if(text.isEmpty()) {
            throw new EmptyFieldException("Text");
        }
        if (fkIdSender.isEmpty()) {
            throw new EmptyFieldException("Sender ID");
        }
        if (senderService.get(Long.valueOf(fkIdSender)) == null) {
            throw new NonexistentFkException("Sender ID", fkIdSender, "Sender");
        }
        Mailing mailing = new Mailing();
        mailing.setText(text);
        mailing.setFkIdSender(Long.valueOf(fkIdSender));
        mailingService.insert(mailing);
        return "mailings";
    }

    @PostMapping("/update-mailing")
    public String update(@RequestParam Long id, @RequestParam String text, @RequestParam String fkIdSender) {
        if (id == null) {
            throw new RuntimeException("Something went wrong: ID id null during update");
        }
        if(text.isEmpty()) {
            throw new EmptyFieldException("Text");
        }
        if (fkIdSender.isEmpty()) {
            throw new EmptyFieldException("Sender ID");
        }
        if (senderService.get(Long.valueOf(fkIdSender)) == null) {
            throw new NonexistentFkException("Sender ID", fkIdSender, "Sender");
        }
        Mailing mailing = new Mailing();
        mailing.setId(id);
        mailing.setText(text);
        mailing.setFkIdSender(Long.valueOf(fkIdSender));
        mailingService.insert(mailing);
        return "mailings";
    }

    @PostMapping("/delete-mailing")
    public String delete(@RequestParam Long id, Model model) {
        mailingService.delete(id);
        List<Mailing> mailings = mailingService.getAll();
        model.addAttribute("mailings", mailings);
        return "mailings";
    }
}
