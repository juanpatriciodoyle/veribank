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
     * @return -> Updated Account or error
     */
    public AccountResponse deposit(String id, int money) {
        if (validatorService.isNegative(money))
            return new AccountResponse("Deposit amount cannot be negative");

        Optional<Account> account = accountService.getAccount(id);
        if (account.isEmpty())
            return new AccountResponse(id + " not found");

        return new AccountResponse(accountService.deposit(id, money));
    }

    /**
     * Withdraw money from an account
     *
     * @param id    -> Identifier of the client account
     * @param money -> The amount of money to withdrawal
     * @return -> Updated Account or error message
     */
    public AccountResponse withdrawal(String id, int money) {
        if (validatorService.isNegative(money))
            return new AccountResponse("Withdraw amount cannot be negative");

        Optional<Account> account = accountService.getAccount(id);
        if (account.isEmpty())
            return new AccountResponse(id + " not found");

        if (validatorService.isTooMuchWithdrawal(account.get(), money))
            return new AccountResponse("Withdraw bigger than the current account balance");


        return new AccountResponse(accountService.withdrawal(id, money));
    }

}
