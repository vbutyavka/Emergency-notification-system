package org.ens.requestservice.controller;

import org.ens.requestservice.entity.*;
import org.ens.requestservice.enums.MailStatus;
import org.ens.requestservice.exceptions.EmptyFieldException;
import org.ens.requestservice.service.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    SenderService senderService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        if (login == null || login.isEmpty()) {
            throw new RuntimeException("You are not logged in");
        }
        Long senderId = senderService.getIdByLogin(login);
        if (senderId == null) {
            throw new RuntimeException("You are not logged in");
        }
        Mailing mailing = new Mailing();
        mailing.setText(message);
        mailing.setFkIdSender(senderId);
        mailingService.insert(mailing);


        List<String> territories = new ArrayList<>();
        List<Recipient> recipients = new ArrayList<>();
        if (localDistricts != null && !localDistricts.isEmpty()) {
            for (Long localDistrictId : localDistricts) {
                LocalDistrict ld = localDistrictService.get(localDistrictId);
                if (ld != null) {
                    territories.add(ld.getName());
                    rabbitTemplate.convertAndSend(message);
                    recipients.addAll(recipientService.getAllByLocalDistrict(ld.getId()));
                }
            }
        }
        else if (regions != null && !regions.isEmpty()) {
            for (Long regionId : regions) {
                Region region = regionService.get(regionId);
                if (region != null) {
                    territories.add(region.getName());
                    recipients.addAll(recipientService.getAllByRegion(region.getId()));
                }
            }
        }
        else if (federalDistricts != null && !federalDistricts.isEmpty()) {
            for (Long federalDistrictId : federalDistricts) {
                FederalDistrict federalDistrict = federalDistrictService.get(federalDistrictId);
                if (federalDistrict != null) {
                    territories.add(federalDistrict.getName());
                    recipients.addAll(recipientService.getAllByFederalDistrict(federalDistrict.getId()));
                }
            }
        }

        for (Recipient recipient : recipients) {
            Mail mail = new Mail();
            mail.setFkIdMailing(mailing.getId());
            mail.setFkIdRecipient(recipient.getId());
            mail.setStatus(MailStatus.CREATED);
            mailService.insert(mail);
            rabbitTemplate.convertAndSend(mail);
        }

        model.addAttribute("territories", territories);
        model.addAttribute("message", message);
        return "sent-response";
    }
}