package co.com.atm.usecase.checkbalance;

import co.com.atm.model.account.Account;
import co.com.atm.model.account.gateways.AccountRepository;
import co.com.atm.usecase.exceptions.AccountNotFoundException;
import co.com.atm.usecase.exceptions.InsufficientBalanceException;
import co.com.atm.usecase.validateaccountexistence.ValidateAccountExistenceUseCase;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class CheckBalanceUseCase {

    private final AccountRepository accountRepository;
    private final ValidateAccountExistenceUseCase accountExistenceUseCase;
    public void validateBalance(Long accountId, BigDecimal amount) {

        Account account = accountExistenceUseCase.validateAccountExistence(accountId);

        BigDecimal currentBalance = account.getBalance();

        if (currentBalance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance in the account. Current balance: " + currentBalance);
        }
    }
}
