package com.vti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.vti.entity.Account;
import com.vti.service.IAccountService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping(value = "api/v1/accounts")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @GetMapping()
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping(value = "/{id}")
    public Account getAccountByID(@PathVariable(name = "id") int id) {
        return accountService.getAccountByID(id);
    }

    @GetMapping(value = "/{name}")
    public List<Account> getAccountByName(@PathVariable(name = "name") String name) {
        return accountService.getAccountByFullName(name);
    }

    @PostMapping()
    public void createAccount(@RequestBody Account account) {
        accountService.createAccount(account);
    }

    @PutMapping(value = "/{id}")
    public void updateAccount(@PathVariable(name = "id") int id, @RequestBody Account account) {
        account.setAccountId(id);
        accountService.updateAccount(account);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAccount(@PathVariable(name = "id") int id) {
        accountService.deleteAccount(id);
    }
}
