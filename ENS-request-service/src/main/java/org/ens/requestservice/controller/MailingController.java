package org.ens.requestservice.controller;

import org.ens.requestservice.controller.crud.RdController;
import org.ens.requestservice.entity.Mailing;
import org.ens.requestservice.exceptions.EmptyFieldException;
import org.ens.requestservice.exceptions.NonexistentFkException;
import org.ens.requestservice.service.MailingService;
import org.ens.requestservice.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emergency")
public class MailingController extends RdController<Mailing, MailingService> {

    @Autowired
    MailingService mailingService;

    @Autowired
    SenderService senderService;

    protected MailingController(MailingService service) {
        super(service);
    }

    @GetMapping("/mailings")
    @Override
    public String getAll(Model model) {
        List<Mailing> mailings = mailingService.getAll();
        model.addAttribute("mailings", mailings);
        return "mailings";
    }

    @GetMapping("/mailings/{id}")
    @Override
    public String get(@PathVariable Long id, Model model) {
        Mailing mailing = mailingService.get(id);
        model.addAttribute("mailings", mailing);
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

    @GetMapping("/delete-mailing/{id}")
    @Override
    public String delete(@PathVariable Long id, Model model) {
        mailingService.delete(id);
        List<Mailing> mailings = mailingService.getAll();
        model.addAttribute("mailings", mailings);
        return "mailings";
    }
}
