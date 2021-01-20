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
class VeribankTest {

    private static VeribankController veribankController;

    @BeforeAll
    static void setup() {
        veribankController = new VeribankController(new AccountServiceImpl(), new ValidatorServiceImpl());
    }

    @Test
    @Order(1)
    void deposit_Ok() {
        AccountResponse accountResponse = veribankController.deposit("francisco", 10);
        Account francisco = accountResponse.getAccount();

        //Client found test
        Assertions.assertAll("deposit feature",
                () -> assertEquals("francisco", francisco.getId()),
                () -> assertEquals(110, francisco.getBalance())
        );

    }

    @Test
    @Order(2)
    void deposit_NotFound() {
        //Client not found test
        AccountResponse accountResponse = veribankController.deposit("eugenia", 10);
        Account eugenia = accountResponse.getAccount();

        Assertions.assertAll("(client not found) deposit feature",
                () -> assertNull(accountResponse.getAccount()),
                () -> assertEquals("eugenia not found", accountResponse.getErrorMessage())
        );

    }

    @Test
    @Order(3)
    void deposit_NegativeAmount() {
        //Client deposit negative amount
        AccountResponse accountResponse = veribankController.deposit("francisco", -10);

        Assertions.assertAll("(negative amount) deposit feature",
                () -> assertNull(accountResponse.getAccount()),
                () -> assertEquals("Deposit amount cannot be negative", accountResponse.getErrorMessage())
        );
    }
}
