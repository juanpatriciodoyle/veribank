package main.controller;

import main.model.Account;
import main.service.AccountService;
import main.service.ValidatorService;
import main.to.AccountResponse;

import java.util.Optional;

public class VeribankController {

    private final AccountService accountService;
    private final ValidatorService validatorService;

    public VeribankController(AccountService accountService, ValidatorService validatorService) {
        this.accountService = accountService;
        this.validatorService = validatorService;
    }

    /**
     * Deposit money into an account
     *
     * @param id    -> Identifier of the client account
     * @param money -> The amount of money to deposit
     * @return -> Updated Account
     */
    public AccountResponse deposit(String id, int money) {
        if (!validatorService.depositAmount(money)){
            return new AccountResponse("Deposit amount cannot be negative");
        }

        Optional<Account> account =  accountService.deposit(id, money);
        return account.map(AccountResponse::new).orElseGet(() -> new AccountResponse(id + " not found"));
    }
}
