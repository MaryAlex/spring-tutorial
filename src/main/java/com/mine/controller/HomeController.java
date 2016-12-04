package com.mine.controller;

import com.mine.model.User;
import com.mine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = GET)
    public ModelAndView home() {
        ModelAndView homeMAV = new ModelAndView("home");
        List<User> users = this.userService.getUsers();
        homeMAV.addObject("users", users);
        return homeMAV;
    }
}
