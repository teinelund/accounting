package org.teinelund.application.accounting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This controller handles pages that does not require an authenticated user.
 *
 * @author Henrik Teinelund
 */
@Controller
public class AnonymousController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String defaultPaget() {
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
