package co.com.atm.usecase.makedeposit;

import co.com.atm.model.account.Account;
import co.com.atm.model.account.gateways.AccountRepository;
import co.com.atm.model.transaction.Transaction;
import co.com.atm.model.transactiontype.TransactionType;
import co.com.atm.usecase.accountusecase.AccountUsecaseUseCase;
import co.com.atm.usecase.checkbalance.CheckBalanceUseCase;
import co.com.atm.usecase.exceptions.AccountNotFoundException;
import co.com.atm.usecase.maketransfer.MakeTransferUseCase;
import lombok.RequiredArgsConstructor;


import java.math.BigDecimal;

@RequiredArgsConstructor
public class MakeDepositUseCase {
    private final AccountRepository accountRepository;
    private final CheckBalanceUseCase checkBalanceUseCase;
    private final MakeTransferUseCase makeTransferUseCase;
    private final AccountUsecaseUseCase accountUsecaseUseCase;

    public Transaction makeDeposit(Long accountId, BigDecimal amount) throws AccountNotFoundException {

        Account account = accountUsecaseUseCase.validateAccountExistence(accountId);

        checkBalanceUseCase.validateBalance(accountId, amount);

        BigDecimal newBalance = account.getBalance().add(amount);

        Transaction deposit = makeTransferUseCase.makeTransaction(account, TransactionType.DEPOSIT, amount, newBalance);

        account.getTransactionHistory().add(deposit);

        accountUsecaseUseCase.saveAccount(account);

        return deposit;

    }




}
