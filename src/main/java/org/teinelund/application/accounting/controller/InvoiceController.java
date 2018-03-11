package org.teinelund.application.accounting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String hello(Model model) {
        return "invoice/list";
    }
}
