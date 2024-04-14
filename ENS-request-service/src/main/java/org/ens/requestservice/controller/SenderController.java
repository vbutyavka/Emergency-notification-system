package org.ens.requestservice.controller;

import org.ens.requestservice.controller.crud.RdController;
import org.ens.requestservice.entity.Sender;
import org.ens.requestservice.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emergency")
public class SenderController extends RdController<Sender, SenderService> {
    
    @Autowired
    SenderService senderService;

    protected SenderController(SenderService service) {
        super(service);
    }

    @GetMapping("/senders")
    @Override
    public String getAll(Model model) {
        List<Sender> senders = senderService.getAll();
        model.addAttribute("senders", senders);
        return "senders";
    }

    @GetMapping("/senders/{id}")
    @Override
    public String get(@PathVariable Long id, Model model) {
        Sender sender =  senderService.get(id);
        model.addAttribute("sender", sender);
        return "edit-sender";
    }

    @PostMapping("/senders")
//    @Override
    public Sender add(@RequestBody Sender sender) {
        senderService.insert(sender);
        return sender;
    }

    @PostMapping("/update-sender")
//    @Override
    public String update(@RequestBody Sender sender) {
        senderService.insert(sender);
        return "redirect:/emergency/senders";
    }

    @GetMapping("/delete-sender/{id}")
    @Override
    public String delete(@PathVariable Long id, Model model) {
        senderService.delete(id);
        List<Sender> senders = senderService.getAll();
        model.addAttribute("senders", senders);
        return "senders";
    }
}
