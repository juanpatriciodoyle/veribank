package main.controller;

import main.model.Account;
import main.service.AccountService;
import main.service.ValidatorService;
import main.to.AccountResponse;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class VeribankController implements VeribankControllerApi {

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
            return new AccountResponse("Deposit amount cannot be zero or less");

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
    @Override
    public AccountResponse withdrawal(String id, int money) {
        if (validatorService.isNegative(money))
            return new AccountResponse("Withdraw amount cannot be zero or less");

        Optional<Account> account = accountService.getAccount(id);
        if (account.isEmpty())
            return new AccountResponse(id + " not found");

        if (validatorService.isTooMuchWithdrawal(account.get(), money))
            return new AccountResponse("Withdraw bigger than the current account balance");


        return new AccountResponse(accountService.withdrawal(id, money));
    }

    /**
     * Transfer money from an account to other existing account
     *
     * @param originId      -> Identifier of the origin client account
     * @param money         -> The amount of money to transfer
     * @param destinationId -> Identifier of the destination client account
     * @return -> Updated Accounts or error message
     */
    @Override
    public List<AccountResponse> transfer(String originId, int money, String destinationId) {
        if (validatorService.isNegative(money))
            return Collections.singletonList(new AccountResponse("Transfer amount cannot be zero or less"));

        Optional<Account> originAccount = accountService.getAccount(originId);
        Optional<Account> destinationAccount = accountService.getAccount(destinationId);
        if (originAccount.isEmpty())
            return Collections.singletonList(new AccountResponse(originId + " not found"));
        if (destinationAccount.isEmpty())
            return Collections.singletonList(new AccountResponse(destinationId + " not found"));

        if (validatorService.isTooMuchWithdrawal(originAccount.get(), money))
            return Collections.singletonList(new AccountResponse("Transfer bigger than the current account balance"));

        List<AccountResponse> accountResponseList = new ArrayList<>();
        accountService.transfer(originId, money, destinationId).forEach(account -> accountResponseList.add(new AccountResponse(account)));

        return accountResponseList;
    }

}
