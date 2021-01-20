package main.to;

import main.model.Account;

/**
 * This class is used in the VeribankController.deposit method response
 * Contemplates errors
 */
public class AccountResponse {

    private Account account;
    private String errorMessage;

    public AccountResponse(Account account, String errorMessage) {
        this.account = account;
        this.errorMessage = errorMessage;
    }

    public AccountResponse(Account account) {
        this.account = account;
    }

    public AccountResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public AccountResponse() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}