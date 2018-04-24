package org.teinelund.application.accounting.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.teinelund.application.accounting.entities.AccUserEntity;
import org.teinelund.application.accounting.entities.BankAccountEntity;
import org.teinelund.application.accounting.entities.InvoiceEntity;
import org.teinelund.application.accounting.model.BankAccount;
import org.teinelund.application.accounting.model.BankAccountAdd;
import org.teinelund.application.accounting.model.Invoice;
import org.teinelund.application.accounting.repository.AccUserRepository;
import org.teinelund.application.accounting.repository.BankAccountRepository;
import org.teinelund.application.accounting.repository.InvoiceRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class InvoiceService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private AccUserRepository accUserRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    public void addBankAccount(BankAccountAdd bankAccountAdd) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AccUserEntity accUserEntity = accUserRepository.getAccUserEntityForUserName(user.getUsername());

        BankAccountEntity bankAccountEntity = new BankAccountEntity();
        bankAccountEntity.setName(bankAccountAdd.getName());
        bankAccountEntity.setDescription(bankAccountAdd.getDescription());
        bankAccountEntity.setAccUserByUserId(accUserEntity);

        bankAccountRepository.save(bankAccountEntity);
    }

    public List<BankAccount> getBankAccounts() {
        List<BankAccount> list = new LinkedList<>();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AccUserEntity accUserEntity = accUserRepository.getAccUserEntityForUserName(user.getUsername());
        Set<BankAccountEntity> set = bankAccountRepository.findBankAccountsByUserId(accUserEntity.getUserId().longValue());
        for (BankAccountEntity bankAccountEntity : set) {
            list.add(new BankAccount(bankAccountEntity.getName(), bankAccountEntity.getDescription(), bankAccountEntity.getId().toString()));
        }
        return list;
    }

    public void deleteBankAccount(String id) {
        bankAccountRepository.delete(Long.parseLong(id));
    }

    public BankAccount editBankAccount(String id) {
        BankAccountEntity bankAccountEntity = bankAccountRepository.findOne(Long.parseLong(id));
        BankAccount bankAccount = new BankAccount(bankAccountEntity.getName(), bankAccountEntity.getDescription(), bankAccountEntity.getId().toString());
        return bankAccount;
    }

    public void updateBankAccount(BankAccount bankAccount) {
        BankAccountEntity bankAccountEntity = new BankAccountEntity();
        bankAccountEntity.setName(bankAccount.getName());
        bankAccountEntity.setDescription(bankAccount.getDescription());
        bankAccountEntity.setId(new Long(bankAccount.getId()));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AccUserEntity accUserEntity = accUserRepository.getAccUserEntityForUserName(user.getUsername());
        bankAccountEntity.setAccUserByUserId(accUserEntity);
        bankAccountRepository.save(bankAccountEntity);
    }

    public List<Invoice> getInvoices(String id) {
        List<Invoice> list = new LinkedList<>();
        Set<InvoiceEntity> set = invoiceRepository.findInvoicesByBankAccountId(Long.parseLong(id));
        for (InvoiceEntity invoice : set) {
            list.add(
                    new Invoice.Builder().
                            setName(invoice.getName()).
                            setGiroName(invoice.getGiro().getName()).
                            setGiroNumber(invoice.getGiro().getGironumber()).
                            setGiroType(invoice.getGiro().getGiroType().toString()).
                            setAmount(invoice.getAmount()).
                            setOcrNumber(invoice.getOcr().getGironumber()).
                            setPayDate(invoice.getPayDate()).
                            build()
                            );

        }
        return list;
    }
}
