package main.service.impl;


import main.model.Account;
import main.service.AccountService;

import java.util.HashMap;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {

    /**
     * Using a HashMap as a fake bank db
     */
    private final HashMap<String, Integer> bankAccounts;

    /**
     * Initializing db as Iteration 1 US demands
     */
    public AccountServiceImpl() {
        this.bankAccounts = new HashMap<>();
        this.bankAccounts.put("francisco", 100);
    }

    /**
     * Deposit money into an existing account
     *
     * @param id    -> Identifier of the client account
     * @param money -> The amount of money to deposit
     * @return -> Updated Account
     * Account not found -> Optional.empty()
     */
    @Override
    public Optional<Account> deposit(String id, int money) {
        if (getAccount(id).isEmpty()) return Optional.empty();
        bankAccounts.put(id, bankAccounts.get(id) + money);
        return getAccount(id);
    }

    /**
     * Withdraw money into an existing account
     *
     * @param id    -> Identifier of the client account
     * @param money -> The amount of money to withdrawal
     * @return -> Updated Account
     * Account not found -> Optional.empty()
     */
    @Override
    public Optional<Account> withdrawal(String id, int money) {
        if (getAccount(id).isEmpty()) return Optional.empty();
        bankAccounts.put(id, bankAccounts.get(id) - money);
        return getAccount(id);
    }

    /**
     * Looks up in the HashMap the id requested
     *
     * @param id -> Identifier of the account
     * @return Account found -> The account demanded
     * Account not found -> Empty
     */
    private Optional<Account> getAccount(String id) {
        Integer balance = bankAccounts.get(id);
        if (balance == null) return Optional.empty();
        return Optional.of(new Account(id, balance));
    }
}
