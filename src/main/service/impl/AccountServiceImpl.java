package main.service.impl;


import main.model.Account;
import main.service.AccountService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
        this.bankAccounts.put("florencia", 50);
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
    public Account deposit(String id, int money) {
        bankAccounts.put(id, bankAccounts.get(id) + money);
        return getAccount(id).orElse(new Account());
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
    public Account withdrawal(String id, int money) {
        bankAccounts.put(id, bankAccounts.get(id) - money);
        return getAccount(id).orElse(new Account());
    }

    /**
     * Looks up in the HashMap the id requested
     *
     * @param id -> Identifier of the account
     * @return Account found -> The account demanded
     * Account not found -> Empty
     */
    @Override
    public Optional<Account> getAccount(String id) {
        Integer balance = bankAccounts.get(id);
        if (balance == null) return Optional.empty();
        return Optional.of(new Account(id, balance));
    }

    /**
     * Takes the desired amount from the origin account and add it to the destination account
     *
     * @param originId      -> Identifier of the origin client account
     * @param money         -> The amount of money to transfer
     * @param destinationId -> Identifier of the destination client account
     * @return -> Updated accounts or a list with empty accounts
     */
    @Override
    public List<Account> transfer(String originId, int money, String destinationId) {
        bankAccounts.put(originId, bankAccounts.get(originId) - money);
        bankAccounts.put(destinationId, bankAccounts.get(destinationId) + money);

        Account origin = getAccount(originId).orElse(new Account());
        Account destination = getAccount(destinationId).orElse(new Account());
        return Arrays.asList(origin,destination);
    }
}
