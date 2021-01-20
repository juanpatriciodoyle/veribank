package main.controller;

import main.model.Account;
import main.service.AccountService;

public class VeribankController {

    private final AccountService accountService;

    public VeribankController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Deposit money into an account
     *
     * @param id    -> Identifier of the client account
     * @param money -> The amount of money to deposit
     * @return -> Updated Account
     */
    public Account deposit(String id, int money) {
        return accountService.deposit(id, money).orElse(new Account());
    }
}
