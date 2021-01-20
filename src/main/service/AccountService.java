package main.service;

import main.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Account deposit(String id, int money);

    Account withdrawal(String id, int money);

    Optional<Account> getAccount(String id);

    List<Account> transfer(String originId, int money, String destinationId);
}
