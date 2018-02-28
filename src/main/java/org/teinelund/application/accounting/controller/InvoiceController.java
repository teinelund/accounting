package org.teinelund.application.accounting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @RequestMapping("/list")
    public String hello(Model model) {
        return "invoice/list";
    }
}
