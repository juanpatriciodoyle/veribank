package main.service;

import main.model.Account;

import java.util.Optional;

public interface AccountService {

    Optional<Account> deposit(String id, int money);
}
