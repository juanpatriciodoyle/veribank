# <div align="center"> ![pagers](src/main/resources/logo.png)</div>
![Days](https://img.shields.io/static/v1?label=Working-Days&message=1&color=blue)
![Status](https://img.shields.io/static/v1?label=Done&message=100%&color=green)

*See [Challenge](src/main/resources/Challenge.md) for the details of the tasks.*


Backend of bank simple project.


   
### Examples
##### Deposit feature
```java
class veribankTest {

    void deposit_Ok() {
        VeribankController veribankController = new VeribankController(new AccountServiceImpl(), new ValidatorServiceImpl());
        AccountResponse accountResponse = veribankController.deposit("francisco", 10);
    }
}
```

##### Withdrawal feature
```java
class veribankTest {

    void withdrawal_Ok() {
        VeribankController veribankController = new VeribankController(new AccountServiceImpl(), new ValidatorServiceImpl());
        AccountResponse accountResponse = veribankController.withdrawal("francisco", 10);
    }
}
```

##### Transfer feature
```java
class veribankTest {

    void transfer_Ok() {
        VeribankController veribankController = new VeribankController(new AccountServiceImpl(), new ValidatorServiceImpl());
        List<AccountResponse> accountResponseList = veribankController.transfer("francisco", 10, "florencia");
    }
}
```

##HashMap used as a db:

####·Francisco

id: francisco

balance: 100

####·Florencia

id: florencia

balance: 50

###Available services:
1. Deposit
2. Withdraw
3. Transfer

*See the tests for the example implementation, else, use the frontend: veribank-front*

Coded with ❤️ by Juan Patricio Doyle ✨2021

