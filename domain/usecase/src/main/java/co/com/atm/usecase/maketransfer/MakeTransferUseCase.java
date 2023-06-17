package co.com.atm.usecase.maketransfer;

import co.com.atm.model.account.Account;
import co.com.atm.model.transaction.Transaction;
import co.com.atm.model.transaction.gateways.TransactionRepository;
import co.com.atm.model.transactiontype.TransactionType;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class MakeTransferUseCase {
    private final TransactionRepository transactionRepository;

    public Transaction makeTransaction(Account  account, TransactionType transactionType, BigDecimal amount, BigDecimal finalBalance){
        Transaction transaction = Transaction
                .builder()
                .account(account)
                .transactionType(transactionType)
                .amount(amount)
                .finalBalance(finalBalance)
                .build();
        return transactionRepository.save(transaction);
    }
}
