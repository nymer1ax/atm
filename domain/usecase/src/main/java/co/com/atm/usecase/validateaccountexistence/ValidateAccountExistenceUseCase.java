package co.com.atm.usecase.validateaccountexistence;

import co.com.atm.model.account.Account;
import co.com.atm.model.account.gateways.AccountRepository;
import co.com.atm.usecase.exceptions.AccountNotFoundException;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class ValidateAccountExistenceUseCase {

    private final AccountRepository accountRepository;

    public Account validateAccountExistence(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(()-> new AccountNotFoundException("Account not found"));
        return account;
    }
}
