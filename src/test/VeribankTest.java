package test;

import main.controller.VeribankController;
import main.model.Account;
import main.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VeribankTest {

    private static VeribankController veribankController;

    @BeforeAll
    static void setup() {
        veribankController = new VeribankController(new AccountServiceImpl());
    }

    @Test
    @Order(1)
    void deposit() {
        Account francisco = veribankController.deposit("francisco", 10);

        Assertions.assertAll("deposit feature",
                () -> assertEquals("francisco", francisco.getId()),
                () -> assertEquals(110, francisco.getBalance())
        );

        Account eugenia = veribankController.deposit("eugenia", 10);

        Assertions.assertAll("(client not found) deposit feature",
                () -> assertNull(eugenia.getId()),
                () -> assertEquals(0, eugenia.getBalance())
        );
    }
}
