package co.com.atm.usecase.checktransactionhistory;

import co.com.atm.model.account.Account;
import co.com.atm.model.transaction.Transaction;
import co.com.atm.usecase.accountusecase.AccountUsecaseUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CheckTransactionHistoryUseCase {

    private final AccountUsecaseUseCase accountUsecaseUseCase;

    public List<Transaction> getTransactionHistoryByAccount(Long accountId){
        Account account = accountUsecaseUseCase.validateAccountExistence(accountId);
        return account.getTransactionHistory();
    }

    public List<Transaction> getTransactionHistory(){
        List<Account> accounts = accountUsecaseUseCase.accounts();

        return accounts.stream()
                .flatMap(account -> account.getTransactionHistory().stream())
                .collect(Collectors.toList());
    }




}
