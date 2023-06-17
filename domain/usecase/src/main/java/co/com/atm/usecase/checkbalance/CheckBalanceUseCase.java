package co.com.atm.usecase.checkbalance;

import co.com.atm.model.account.Account;
import co.com.atm.model.account.gateways.AccountRepository;
import co.com.atm.usecase.accountusecase.AccountUsecaseUseCase;
import co.com.atm.usecase.exceptions.InsufficientBalanceException;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class CheckBalanceUseCase {

    private final AccountUsecaseUseCase accountUsecaseUseCase;
    public void validateBalance(Long accountId, BigDecimal amount) {

        Account account = accountUsecaseUseCase.validateAccountExistence(accountId);

        BigDecimal currentBalance = account.getBalance();

        if (currentBalance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance in the account. Current balance: " + currentBalance);
        }
    }
}
