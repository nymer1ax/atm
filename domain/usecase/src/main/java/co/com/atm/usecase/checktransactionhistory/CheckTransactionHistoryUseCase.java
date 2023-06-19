package co.com.atm.usecase.checktransactionhistory;

import co.com.atm.model.account.Account;
import co.com.atm.model.transaction.Transaction;
import co.com.atm.model.transaction.gateways.TransactionRepository;
import co.com.atm.usecase.accountusecase.AccountUsecaseUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CheckTransactionHistoryUseCase {

    private final AccountUsecaseUseCase accountUsecaseUseCase;

    private final TransactionRepository transactionRepository;

    public List<Transaction> getTransactionHistoryByAccount(Long accountId){
        Account account = accountUsecaseUseCase.validateAccountExistence(accountId);
       return transactionRepository.findAllByAccountId(accountId);

    }

    public List<Transaction> getAll(){
        return transactionRepository.findAllTransactions();
    }






}
