package test;

import main.controller.VeribankController;
import main.model.Account;
import main.service.impl.AccountServiceImpl;
import main.service.impl.ValidatorServiceImpl;
import main.to.AccountResponse;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WithdrawTest {

    private static VeribankController veribankController;

    @BeforeAll
    static void setup() {
        veribankController = new VeribankController(new AccountServiceImpl(), new ValidatorServiceImpl());
    }

    @Test
    @Order(1)
    void withdrawal_Ok() {
        AccountResponse accountResponse = veribankController.withdrawal("francisco", 10);
        Account francisco = accountResponse.getAccount();

        Assertions.assertAll("withdrawal feature",
                () -> assertEquals("francisco", francisco.getId()),
                () -> assertEquals(90, francisco.getBalance())
        );

    }

    @Test
    @Order(2)
    void withdrawal_NotFound() {
        AccountResponse accountResponse = veribankController.withdrawal("eugenia", 10);

        Assertions.assertAll("(client not found) Withdrawal feature",
                () -> assertNull(accountResponse.getAccount()),
                () -> assertEquals("eugenia not found", accountResponse.getErrorMessage())
        );

    }

    @Test
    @Order(3)
    void withdrawal_NegativeAmount() {
        AccountResponse accountResponse = veribankController.withdrawal("francisco", -10);

        Assertions.assertAll("(negative amount) Withdrawal feature",
                () -> assertNull(accountResponse.getAccount()),
                () -> assertEquals("Withdraw amount cannot be negative", accountResponse.getErrorMessage())
        );
    }

    @Test
    @Order(4)
    void withdrawal_TooMuchWithdrawal() {
        AccountResponse accountResponse = veribankController.withdrawal("francisco", 150);

        Assertions.assertAll("(Too much Withdrawal) Withdrawal feature",
                () -> assertNull(accountResponse.getAccount()),
                () -> assertEquals("Withdraw bigger than the current account balance", accountResponse.getErrorMessage())
        );
    }
}
