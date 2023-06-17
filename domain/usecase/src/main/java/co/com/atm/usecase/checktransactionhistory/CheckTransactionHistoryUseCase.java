package co.com.atm.usecase.checktransactionhistory;

import co.com.atm.model.account.Account;
import co.com.atm.model.account.gateways.AccountRepository;
import co.com.atm.model.transaction.Transaction;
import co.com.atm.model.transaction.gateways.TransactionRepository;
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

    public List<Transaction> getTransactionHistoryOfOtherAccount(Long accountId) {
        Account account = accountUsecaseUseCase.validateAccountExistence(accountId);

        return getAllTransactionsOfOtherAccounts(account);
    }

    public List<Transaction> getTransactionHistory(){
        List<Account> accounts = accountUsecaseUseCase.accounts();

        return accounts.stream()
                .flatMap(account -> account.getTransactionHistory().stream())
                .collect(Collectors.toList());
    }

    private List<Transaction> getAllTransactionsOfOtherAccounts(Account account) {
        List<Account> otherAccounts = accountUsecaseUseCase.otherAccounts(account.getId());

        return accountUsecaseUseCase.findAllByIn(otherAccounts).stream()
                .flatMap(otherAccount -> otherAccount.getTransactionHistory().stream())
                .collect(Collectors.toList());
    }


}
