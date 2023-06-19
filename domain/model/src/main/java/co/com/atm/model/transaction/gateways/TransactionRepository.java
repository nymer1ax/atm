package co.com.atm.model.transaction.gateways;

import co.com.atm.model.transaction.Transaction;

public interface TransactionRepository {
    Transaction saveTransaction(Transaction transaction);
}
