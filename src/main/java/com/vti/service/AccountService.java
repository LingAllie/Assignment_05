package com.vti.service;

import com.vti.entity.Account;

import com.vti.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    public List<Account> getAllAccounts() {return accountRepository.getAllAccounts();}

    public Account getAccountByID(int id) {return accountRepository.getAccountByID(id);}

    public List<Account> getAccountByFullName(String name) {
        return accountRepository.getAccountByFullName(name);
    }

    public void createAccount(Account account) {
        accountRepository.createAccount(account);
    }

    public void updateAccount(Account account) {
        accountRepository.updateAccount(account);
    }

    public void deleteAccount(int id) {
        accountRepository.deleteAccount(id);
    }

    public boolean isAccountExistsByID(int id) {
        return accountRepository.isAccountExistsByID(id);
    }

    public boolean isAccountExistsByFullName(String name) {
        return accountRepository.isAccountExistsByFullName(name);
    }
}
