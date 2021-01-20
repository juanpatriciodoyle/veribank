package main.service;

import main.model.Account;

public interface ValidatorService {

    boolean isNegative(int money);

    boolean isTooMuchWithdrawal(Account account, int money);
}
