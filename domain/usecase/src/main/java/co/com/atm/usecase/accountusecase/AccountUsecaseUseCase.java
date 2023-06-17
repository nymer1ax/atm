package co.com.atm.usecase.accountusecase;

import co.com.atm.model.account.Account;
import co.com.atm.model.account.gateways.AccountRepository;
import co.com.atm.usecase.exceptions.AccountNotFoundException;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class AccountUsecaseUseCase {

    private final AccountRepository accountRepository;

    public Account validateAccountExistence(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(()-> new AccountNotFoundException("Account not found"));
    }

    public Account saveAccount(Account account){
        return accountRepository.save(account);
    }


}
