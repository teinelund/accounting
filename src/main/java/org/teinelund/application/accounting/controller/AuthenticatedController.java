package org.teinelund.application.accounting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This controller handles pages that does require an authenticated user.
 *
 * @author Henrik Teinelund
 */
@Controller
@RequestMapping("/invoice")
public class AuthenticatedController {

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String start(Model model) {
        return "invoice/start";
    }
}
