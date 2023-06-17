package co.com.atm.usecase.maketransfer;

import co.com.atm.model.account.Account;
import co.com.atm.model.transaction.Transaction;
import co.com.atm.model.transaction.gateways.TransactionRepository;
import co.com.atm.model.transactiontype.TransactionType;
import co.com.atm.usecase.accountusecase.AccountUsecaseUseCase;
import co.com.atm.usecase.checkbalance.CheckBalanceUseCase;
import co.com.atm.usecase.makedeposit.MakeDepositUseCase;
import co.com.atm.usecase.makewithdrawal.MakeWithdrawalUseCase;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class MakeTransferUseCase {
    private final TransactionRepository transactionRepository;
    private final AccountUsecaseUseCase accountUsecaseUseCase;
    private final CheckBalanceUseCase checkBalanceUseCase;
    private final MakeWithdrawalUseCase makeWithdrawalUseCase;
    private final MakeDepositUseCase makeDepositUseCase;

    public Transaction makeTransaction(Account  account, TransactionType transactionType, BigDecimal amount, BigDecimal finalBalance, String description){
        Transaction transaction = Transaction
                .builder()
                .account(account)
                .transactionType(transactionType)
                .amount(amount)
                .finalBalance(finalBalance)
                .description(description)
                .build();
        return transactionRepository.save(transaction);
    }

    public void makeTransfer(Long sourceAccountId, Long destinationAccountId, BigDecimal amount){
        Account sourceAccount = accountUsecaseUseCase.validateAccountExistence(sourceAccountId);
        BigDecimal oldBalance = sourceAccount.getBalance();
        Account destinationAccount = accountUsecaseUseCase.validateAccountExistence(destinationAccountId);

        checkBalanceUseCase.validateBalance(sourceAccountId, amount);

        BigDecimal newSourceBalance = sourceAccount.getBalance().subtract(amount);
        sourceAccount.setBalance(newSourceBalance);

        BigDecimal newDestinationBalance = destinationAccount.getBalance().add(amount);
        destinationAccount.setBalance(newDestinationBalance);

        String transferDescription = "Transferencia de " + sourceAccount.getAccountNumber() + " a " + destinationAccount.getAccountNumber();

        Transaction withdrawalTransaction = makeWithdrawalUseCase.makeWithdrawal(sourceAccountId, newSourceBalance);
        Transaction depositTransaction = makeDepositUseCase.makeDeposit(destinationAccountId, newDestinationBalance);
        Transaction transfer = this.makeTransaction(sourceAccount, TransactionType.TRANSFER, oldBalance, newSourceBalance ,transferDescription);

        sourceAccount.getTransactionHistory().add(withdrawalTransaction);
        destinationAccount.getTransactionHistory().add(depositTransaction);
        sourceAccount.getTransactionHistory().add(transfer);

        accountUsecaseUseCase.saveAccount(sourceAccount);
        accountUsecaseUseCase.saveAccount(destinationAccount);


    }
}
