package com.mine.controller;

import com.mine.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HomeController {

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/", method = GET)
    public String home() {
        System.out.println(this.contactService.getContacts().get(0).getName());
        return "home";
    }
}
