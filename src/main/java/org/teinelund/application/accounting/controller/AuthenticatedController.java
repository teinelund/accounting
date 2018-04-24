package org.teinelund.application.accounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.teinelund.application.accounting.model.BankAccount;
import org.teinelund.application.accounting.model.BankAccountAdd;
import org.teinelund.application.accounting.model.Invoice;
import org.teinelund.application.accounting.model.User;
import org.teinelund.application.accounting.services.InvoiceService;

import java.util.LinkedList;
import java.util.List;

/**
 * This controller handles pages that does require an authenticated user.
 *
 * @author Henrik Teinelund
 */
@Controller
@RequestMapping("/invoice")
public class AuthenticatedController {

    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String start(Model model) {
        List<BankAccount> list = invoiceService.getBankAccounts();
        model.addAttribute("bankaccounts", list);
        return "invoice/start";
    }

    @RequestMapping(value = "/add-bank-account", method = RequestMethod.GET)
    public String addBankAccount() {
        return "invoice/add-bank-account";
    }

    @RequestMapping(value = "/add-bank-account", method = RequestMethod.POST)
    public String addBankAccountPost(@ModelAttribute(value="bankAccountAdd") BankAccountAdd bankAccountAdd, Model model) {
        invoiceService.addBankAccount(bankAccountAdd);
        List<BankAccount> list = invoiceService.getBankAccounts();
        model.addAttribute("bankaccounts", list);
        return "invoice/start";
    }

    @RequestMapping(value = "/bank-account/{id}/delete", method = RequestMethod.GET)
    public String deleteBankAccount(@PathVariable String id, Model model) {
        invoiceService.deleteBankAccount(id);
        List<BankAccount> list = invoiceService.getBankAccounts();
        model.addAttribute("bankaccounts", list);
        return "invoice/start";
    }

    @RequestMapping(value = "/bank-account/{id}/edit", method = RequestMethod.GET)
    public String editBankAccount(@PathVariable String id, Model model) {
        BankAccount bankAccount = invoiceService.editBankAccount(id);
        model.addAttribute("bankAccount", bankAccount);
        return "invoice/edit-bank-account";
    }

    @RequestMapping(value = "/edit-bank-account", method = RequestMethod.POST)
    public String editBankAccountPost(@ModelAttribute(value="bankAccountAdd") BankAccount bankAccount, Model model) {
        invoiceService.updateBankAccount(bankAccount);
        List<BankAccount> list = invoiceService.getBankAccounts();
        model.addAttribute("bankaccounts", list);
        return "invoice/start";
    }

    // @{/invoice/bank-account/{id}/balance(id=${bankaccount.id})}
    @RequestMapping(value = "/bank-account/{id}/balance", method = RequestMethod.GET)
    public String balance(@PathVariable String id, Model model) {
        List<Invoice> list = invoiceService.getInvoices(id);
        model.addAttribute("invoices", list);
        return "invoice/balance-bank-account";
    }
}
