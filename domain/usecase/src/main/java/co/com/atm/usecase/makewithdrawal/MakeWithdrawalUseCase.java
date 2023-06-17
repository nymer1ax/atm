package co.com.atm.usecase.makewithdrawal;

import co.com.atm.model.account.Account;
import co.com.atm.model.account.gateways.AccountRepository;
import co.com.atm.model.transaction.Transaction;
import co.com.atm.model.transactiontype.TransactionType;
import co.com.atm.usecase.accountusecase.AccountUsecaseUseCase;
import co.com.atm.usecase.checkbalance.CheckBalanceUseCase;
import co.com.atm.usecase.maketransfer.MakeTransferUseCase;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class MakeWithdrawalUseCase {
    private final AccountUsecaseUseCase accountUsecaseUseCase;
    private final CheckBalanceUseCase checkBalanceUseCase;
    private final MakeTransferUseCase makeTransferUseCase;
    private final AccountRepository accountRepository;

    public Transaction makeWithdrawal(Long accountId, BigDecimal amount){
        Account account = accountUsecaseUseCase.validateAccountExistence(accountId);
        checkBalanceUseCase.validateBalance(accountId, amount);
        BigDecimal newBalance = account.getBalance().subtract(amount);
        account.setBalance(newBalance);
        Transaction withdrawal = makeTransferUseCase.makeTransaction(account, TransactionType.WITHDRAWAL, amount, newBalance, "Withdrawal");
        account.getTransactionHistory().add(withdrawal);
        accountRepository.save(account);
        return withdrawal;
    }


}
