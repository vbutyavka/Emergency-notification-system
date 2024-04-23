package org.ens.requestservice.controller;

import org.ens.requestservice.entity.FederalDistrict;
import org.ens.requestservice.entity.LocalDistrict;
import org.ens.requestservice.entity.Region;
import org.ens.requestservice.exceptions.EmptyFieldException;
import org.ens.requestservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/emergency")
public class NotificationController {

    @Autowired
    FederalDistrictService federalDistrictService;

    @Autowired
    RegionService regionService;

    @Autowired
    LocalDistrictService localDistrictService;

    @Autowired
    MailingService mailingService;

    @Autowired
    MailService mailService;

    @Autowired
    RecipientService recipientService;

    @GetMapping("/home")
    public String home(Model model) {
        List<FederalDistrict> federalDistricts = federalDistrictService.getAll();
        List<Region> regions = regionService.getAll();
        List<LocalDistrict> localDistricts = localDistrictService.getAll();
        model.addAttribute("federalDistricts", federalDistricts);
        model.addAttribute("regions", regions);
        model.addAttribute("localDistricts", localDistricts);
        return "home";
    }

    @PostMapping("/send")
    public String send(
            @RequestParam(name = "localDistricts", required = false) List<Long> localDistricts,
            @RequestParam(name = "regions", required = false) List<Long> regions,
            @RequestParam(name = "federalDistricts", required = false) List<Long> federalDistricts,
            @RequestParam("message") String message,
            Model model) {
        if (message == null || message.isEmpty()) {
            throw new EmptyFieldException("message");
        }
        List<String> territories = new ArrayList<>();
        if (localDistricts != null && !localDistricts.isEmpty()) {
            for (Long localDistrictId : localDistricts) {
                LocalDistrict ld = localDistrictService.get(localDistrictId);
                if (ld != null) {
                    //TODO send to broker
                    territories.add(ld.getName());
                }
            }
        }
        else if (regions != null && !regions.isEmpty()) {
            for (Long regionId : regions) {
                Region region = regionService.get(regionId);
                if (region != null) {
                    //TODO send to broker
                    territories.add(region.getName());
                }
            }
        }
        else if (federalDistricts != null && !federalDistricts.isEmpty()) {
            for (Long federalDistrictId : federalDistricts) {
                FederalDistrict federalDistrict = federalDistrictService.get(federalDistrictId);
                if (federalDistrict != null) {
                    //TODO send to broker
                    territories.add(federalDistrict.getName());
                }
            }
        }
        model.addAttribute("territories", territories);
        model.addAttribute("message", message);
        return "sent-response";
    }
}