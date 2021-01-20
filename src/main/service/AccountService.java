package main.service;

import main.model.Account;

import java.util.Optional;

public interface AccountService {

    Account deposit(String id, int money);

    Account withdrawal(String id, int money);

    Optional<Account> getAccount(String id);
}
