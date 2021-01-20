package main.controller;

import main.to.AccountResponse;

import java.util.List;

public interface VeribankControllerApi {

    AccountResponse deposit(String id, int money);

    AccountResponse withdrawal(String id, int money);

    List<AccountResponse> transfer(String originId, int money, String destinationId);

}
