package main.service.impl;

import main.model.Account;
import main.service.ValidatorService;

public class ValidatorServiceImpl implements ValidatorService {

    /**
     * Validates that the amount is valid
     * @param money -> Money amount
     * @return true if money is equal or grater than 0, else false
     */
    @Override
    public boolean isNegative(int money) {
        return (money <= 0);
    }

    /**
     * Validates that the withdrawal amount is not grater than the balance amount
     * @param money -> Withdrawal amount
     * @return true if money is equal or less than balance amount, else false
     */
    @Override
    public boolean isTooMuchWithdrawal(Account account, int money) {
        return account.getBalance() <= money;
    }

}
