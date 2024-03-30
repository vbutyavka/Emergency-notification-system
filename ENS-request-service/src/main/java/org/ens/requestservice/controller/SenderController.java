package org.ens.requestservice.controller;

import org.ens.requestservice.entity.Sender;
import org.ens.requestservice.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emergency")
public class SenderController implements CrudController<Sender> {
    
    @Autowired
    SenderService senderService;

    @GetMapping("/senders")
    @Override
    public List<Sender> getAll() {
        return senderService.getAll();
    }

    @GetMapping("/senders/{id}")
    @Override
    public Sender get(@PathVariable Long id) {
        return senderService.get(id);
    }

    @PostMapping("/senders")
    @Override
    public Sender add(@RequestBody Sender Sender) {
        senderService.insert(Sender);
        return Sender;
    }

    @PutMapping("/senders")
    @Override
    public Sender update(@RequestBody Sender Sender) {
        senderService.insert(Sender);
        return Sender;
    }

    @DeleteMapping("/senders/{id}")
    @Override
    public Long delete(@PathVariable Long id) {
        senderService.delete(id);
        return id;
    }
}
