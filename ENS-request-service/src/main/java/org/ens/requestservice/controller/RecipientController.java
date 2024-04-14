package org.ens.requestservice.controller;

import org.ens.requestservice.controller.crud.RdController;
import org.ens.requestservice.entity.Recipient;
import org.ens.requestservice.service.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emergency")
public class RecipientController extends RdController<Recipient, RecipientService> {
    
    @Autowired
    RecipientService recipientService;

    protected RecipientController(RecipientService service) {
        super(service);
    }

    @GetMapping("/recipients")
    @Override
    public String getAll(Model model) {
        List<Recipient> recipients =  recipientService.getAll();
        model.addAttribute("recipients", recipients);
        return "recipients";
    }

    @GetMapping("/recipients/{id}")
    @Override
    public String get(@PathVariable Long id, Model model) {
        Recipient recipient =  recipientService.get(id);
        model.addAttribute("recipient", recipient);
        return "edit-recipient";
    }

    @PostMapping("/recipients")
//    @Override
    public Recipient add(@RequestBody Recipient recipient) {
        recipientService.insert(recipient);
        return recipient;
    }

    @PostMapping("/update-recipient")
//    @Override
    public String update(@RequestBody Recipient recipient) {
        recipientService.insert(recipient);
        return "redirect:/emergency/recipients";
    }

    @GetMapping("/delete-recipient/{id}")
    @Override
    public String delete(@PathVariable Long id, Model model) {
        recipientService.delete(id);
        List<Recipient> recipients =  recipientService.getAll();
        model.addAttribute("recipients", recipients);
        return "recipients";
    }
}
