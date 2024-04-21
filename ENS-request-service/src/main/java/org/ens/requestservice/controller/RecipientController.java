package org.ens.requestservice.controller;

import org.ens.requestservice.entity.Recipient;
import org.ens.requestservice.enums.RecipientStatus;
import org.ens.requestservice.exceptions.EmptyFieldException;
import org.ens.requestservice.exceptions.NonexistentFkException;
import org.ens.requestservice.service.LocalDistrictService;
import org.ens.requestservice.service.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/emergency")
public class RecipientController {
    
    @Autowired
    RecipientService recipientService;

    @Autowired
    LocalDistrictService localDistrictService;

    @GetMapping("/recipients")
    public String getAll(@RequestParam(required = false, defaultValue = "0") String from, Model model) {
        long fromLong = Long.parseLong(from);
        List<Recipient> recipients =  recipientService.getBatch(fromLong);
        model.addAttribute("recipients", recipients);
        return "recipients";
    }

    @GetMapping("/recipients/{id}")
    public String get(@PathVariable Long id, Model model) {
        Recipient recipient =  recipientService.get(id);
        model.addAttribute("recipient", recipient);
        return "edit-recipient";
    }

    @PostMapping("/recipients")
    public String add(@RequestParam String phoneNumber, @RequestParam String status, @RequestParam String fkIdLd) {
        if (phoneNumber.isEmpty()) {
            throw new EmptyFieldException("Phone number");
        }
        if (status.isEmpty()) {
            throw new EmptyFieldException("Status");
        }
        if (fkIdLd.isEmpty()) {
            throw new EmptyFieldException("Local district ID");
        }
        if (localDistrictService.get(Long.valueOf(fkIdLd)) == null) {
            throw new NonexistentFkException("Local district ID", fkIdLd, "Local district");
        }
        Recipient recipient = new Recipient();
        recipient.setPhoneNumber(phoneNumber);
        recipient.setStatus(RecipientStatus.valueOf(status));
        recipient.setFkIdLd(Long.valueOf(fkIdLd));
        recipientService.insert(recipient);
        return "recipients";
    }

    @PostMapping("/update-recipient")
    public String update(@RequestParam Long id, @RequestParam String phoneNumber, @RequestParam String status, @RequestParam String fkIdLd) {
        if (id == null) {
            throw new RuntimeException("Something went wrong: ID id null during update");
        }
        if (phoneNumber.isEmpty()) {
            throw new EmptyFieldException("Phone number");
        }
        if (status.isEmpty()) {
            throw new EmptyFieldException("Status");
        }
        if (fkIdLd.isEmpty()) {
            throw new EmptyFieldException("Local district ID");
        }
        if (localDistrictService.get(Long.valueOf(fkIdLd)) == null) {
            throw new NonexistentFkException("Local district ID", fkIdLd, "Local district");
        }
        Recipient recipient = new Recipient();
        recipient.setId(id);
        recipient.setPhoneNumber(phoneNumber);
        recipient.setStatus(RecipientStatus.valueOf(status));
        recipient.setFkIdLd(Long.valueOf(fkIdLd));
        recipientService.insert(recipient);
        return "recipients";
    }

    @PostMapping("/delete-recipient")
    public String delete(@RequestParam Long id, Model model) {
        recipientService.delete(id);
        List<Recipient> recipients =  recipientService.getAll();
        model.addAttribute("recipients", recipients);
        return "recipients";
    }

    @GetMapping("/recipients/number")
    public String getByNumber(@RequestParam String phoneNumberSearch, Model model) {
        Recipient recipient = recipientService.getByPhoneNumber(phoneNumberSearch);
        List<Recipient> recipients = new ArrayList<>();
        recipients.add(recipient);
        model.addAttribute("recipients", recipients);
        return "recipients";
    }
}
