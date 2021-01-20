package test;

import main.controller.VeribankController;
import main.model.Account;
import main.service.impl.AccountServiceImpl;
import main.service.impl.ValidatorServiceImpl;
import main.to.AccountResponse;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TransferTest {

    private static VeribankController veribankController;

    @BeforeAll
    static void setup() {
        veribankController = new VeribankController(new AccountServiceImpl(), new ValidatorServiceImpl());
    }

    @Test
    @Order(1)
    void transfer_Ok() {
        List<AccountResponse> accountResponseList = veribankController.transfer("francisco", 10, "florencia");
        Account francisco = accountResponseList.get(0).getAccount();
        Account florencia = accountResponseList.get(1).getAccount();

        Assertions.assertAll("Transfer feature",
                () -> assertEquals("francisco", francisco.getId()),
                () -> assertEquals(90, francisco.getBalance()),
                () -> assertEquals("florencia", florencia.getId()),
                () -> assertEquals(60, florencia.getBalance())
        );
    }

    @Test
    @Order(2)
    void transfer_OriginNotFound() {
        List<AccountResponse> accountResponseList = veribankController.transfer("eugenia", 10, "florencia");

        Assertions.assertAll("(origin client not found) Transfer feature",
                () -> assertNull(accountResponseList.get(0).getAccount()),
                () -> assertEquals("eugenia not found", accountResponseList.get(0).getErrorMessage())
        );
    }

    @Test
    @Order(3)
    void transfer_DestinationNotFound() {
        List<AccountResponse> accountResponseList = veribankController.transfer("francisco", 10, "gustavo");

        Assertions.assertAll("(destination client not found) Transfer feature",
                () -> assertNull(accountResponseList.get(0).getAccount()),
                () -> assertEquals("gustavo not found", accountResponseList.get(0).getErrorMessage())
        );
    }

    @Test
    @Order(4)
    void transfer_NegativeAmount() {
        List<AccountResponse> accountResponseList = veribankController.transfer("francisco", -10, "florencia");

        Assertions.assertAll("(negative amount) Transfer feature",
                () -> assertNull(accountResponseList.get(0).getAccount()),
                () -> assertEquals("Transfer amount cannot be zero or less", accountResponseList.get(0).getErrorMessage())
        );
    }

    @Test
    @Order(5)
    void transfer_MoneyNotAvailable() {
        List<AccountResponse> accountResponseList = veribankController.transfer("francisco", 150, "florencia");

        Assertions.assertAll("(Too much Withdrawal) Transfer feature",
                () -> assertNull(accountResponseList.get(0).getAccount()),
                () -> assertEquals("Transfer bigger than the current account balance", accountResponseList.get(0).getErrorMessage())
        );
    }

}
