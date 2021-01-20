package main.service.impl;


import main.service.ValidatorService;

public class ValidatorServiceImpl implements ValidatorService {

    /**
     * Validates that the deposit amount is valid
     * @param money -> Deposit amount
     * @return true if money is equal or grater than 0, else false
     */
    @Override
    public boolean isNegative(int money) {
        return (money <= 0);
    }
}
