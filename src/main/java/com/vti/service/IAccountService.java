package com.vti.service;

import java.util.List;

import com.vti.entity.Account;

public interface IAccountService {

    public List<Account> getAllAccounts();

    public Account getAccountByID(int id);

    public List<Account> getAccountByFullName(String name);

    public void createAccount(Account account);

    public void updateAccount(Account account);

    public void deleteAccount(int id);

    public boolean isAccountExistsByID(int id);

    public boolean isAccountExistsByFullName(String name);

}
