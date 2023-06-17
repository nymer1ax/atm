package co.com.atm.usecase.makedeposit;

import co.com.atm.model.account.Account;
import co.com.atm.model.account.gateways.AccountRepository;
import co.com.atm.model.transaction.Transaction;
import co.com.atm.model.transactiontype.TransactionType;
import co.com.atm.usecase.checkbalance.CheckBalanceUseCase;
import co.com.atm.usecase.exceptions.AccountNotFoundException;
import co.com.atm.usecase.maketransfer.MakeTransferUseCase;
import co.com.atm.usecase.validateaccountexistence.ValidateAccountExistenceUseCase;
import lombok.RequiredArgsConstructor;


import java.math.BigDecimal;

@RequiredArgsConstructor
public class MakeDepositUseCase {
    private final AccountRepository accountRepository;
    private final CheckBalanceUseCase checkBalanceUseCase;
    private final MakeTransferUseCase makeTransferUseCase;
    private final ValidateAccountExistenceUseCase accountExistenceUseCase;

    public void makeDeposit(Long accountId, BigDecimal amount) throws AccountNotFoundException {

        Account account = accountExistenceUseCase.validateAccountExistence(accountId);

        checkBalanceUseCase.validateBalance(accountId, amount);

        BigDecimal newBalance = account.getBalance().add(amount);

        Transaction deposit = makeTransferUseCase.makeTransaction(account, TransactionType.DEPOSIT, amount, newBalance);

        account.getTransactionHistory().add(deposit);

        accountRepository.save(account);
    }




}
