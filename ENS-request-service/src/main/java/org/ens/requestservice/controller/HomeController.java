package org.ens.requestservice.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emergency")
public class HomeController {
    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }
}
