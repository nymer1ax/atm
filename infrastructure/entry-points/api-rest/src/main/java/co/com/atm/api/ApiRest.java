package co.com.atm.api;

import co.com.atm.api.dto.AccountDto;
import co.com.atm.api.dto.TransactionDto;
import co.com.atm.api.dto.TransferDto;
import co.com.atm.model.account.Account;
import co.com.atm.model.transaction.Transaction;
import co.com.atm.usecase.accountusecase.AccountUsecaseUseCase;
import co.com.atm.usecase.checktransactionhistory.CheckTransactionHistoryUseCase;
import co.com.atm.usecase.transactions.TransactionsUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
    private final AccountUsecaseUseCase accountUsecaseUseCase;
    private final CheckTransactionHistoryUseCase checkTransactionHistoryUseCase;
    private final TransactionsUseCase transactionsUseCase;


    @GetMapping(path = "/accounts")
    public ResponseEntity<List<Account>> getAccount() {
        return ResponseEntity.ok(accountUsecaseUseCase.accounts());
    }

    @PostMapping(path = "/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody AccountDto account) {
        return ResponseEntity.ok(accountUsecaseUseCase
                .saveAccount(Account
                        .builder()
                        .accountNumber(account.getAccountNumber())
                        .balance(account.getBalance())
                        .build()
                ));
    }

    @GetMapping(path = "/transactions")
    public ResponseEntity<List<Transaction>> getTransactions() {
        return ResponseEntity.ok(checkTransactionHistoryUseCase.getAll());
    }

    @PostMapping(path = "/deposit")
    public ResponseEntity createDeposit(@RequestBody TransactionDto transaction) {
        transactionsUseCase.makeDeposit(transaction.getAccountId(), transaction.getAmount());
        return ResponseEntity.accepted().build();
    }

    @PostMapping(path = "/withdrawal")
    public ResponseEntity makeWithdrawal(@RequestBody TransactionDto transaction) {
        transactionsUseCase.makeWithdrawal(transaction.getAccountId(), transaction.getAmount());
        return ResponseEntity.accepted().build();
    }

    @PostMapping(path = "/transfer")
    public ResponseEntity makeTransfer(@RequestBody TransferDto transaction) {
        transactionsUseCase.makeTransfer(transaction.getSourceAccountId(), transaction.getDestinationAccountId(), transaction.getAmount());
        return ResponseEntity.accepted().build();
    }


}
