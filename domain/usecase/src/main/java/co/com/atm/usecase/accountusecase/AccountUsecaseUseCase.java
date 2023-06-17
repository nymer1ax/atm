package co.com.atm.usecase.accountusecase;

import co.com.atm.model.account.Account;
import co.com.atm.model.account.gateways.AccountRepository;
import co.com.atm.usecase.exceptions.AccountNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class AccountUsecaseUseCase {

    private final AccountRepository accountRepository;

    public Account validateAccountExistence(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account not found"));
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> accounts() {
        return accountRepository.findAll();
    }

    public List<Account> otherAccounts(Long accountId) {
        return accountRepository.findAllOtherAccounts(accountId);
    }

    public List<Account> findAllByIn(List<Account> otherAccounts){
       return accountRepository.findAllByIn(otherAccounts);
    }

}
