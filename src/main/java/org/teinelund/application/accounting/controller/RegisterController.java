package org.teinelund.application.accounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.teinelund.application.accounting.model.User;
import org.teinelund.application.accounting.services.RegisterService;
import org.springframework.ui.Model;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute(value="user") User user, Model model) {
        if (registerService.registerUser(user)) {
            model.addAttribute("registeredUser", true);
            return "index";
        }
        model.addAttribute("error", true);
        return "register";
    }


}
