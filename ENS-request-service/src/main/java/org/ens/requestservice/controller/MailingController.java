package org.ens.requestservice.controller;

import org.ens.requestservice.controller.crud.RdController;
import org.ens.requestservice.entity.Mailing;
import org.ens.requestservice.service.MailingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emergency")
public class MailingController extends RdController<Mailing, MailingService> {

    @Autowired
    MailingService mailingService;

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
//    @Override
    public Mailing add(@RequestBody Mailing mailing) {
        mailingService.insert(mailing);
        return mailing;
    }

    @PostMapping("/update-mailing")
//    @Override
    public String update(@RequestBody Mailing mailing) {
        mailingService.insert(mailing);
        return "redirect:/emergency/mailings";
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
