package org.teinelund.application.accounting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String defaultPaget(Model model) {
        return "index";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        return "index";
    }
}
