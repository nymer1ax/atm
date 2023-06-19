package co.com.atm.jpa.transaction;

import co.com.atm.jpa.account.AccountDataEntity;
import co.com.atm.jpa.helper.AdapterOperations;
import co.com.atm.jpa.mapper.MapperObject;
import co.com.atm.model.account.Account;
import co.com.atm.model.transaction.Transaction;
import co.com.atm.model.transaction.gateways.TransactionRepository;
import jakarta.transaction.Transactional;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionAdapterRepository extends AdapterOperations<Transaction, TransactionDataEntity, Long, TransactionDataRepository> implements TransactionRepository {

    private final MapperObject mapperObjects;


    public TransactionAdapterRepository(TransactionDataRepository repository, ObjectMapper mapper, MapperObject mapperObjects) {
        super(repository, mapper, d -> mapper.map(d, Transaction.class));
        this.mapperObjects = mapperObjects;
    }

    @Override
    @Transactional
    public Transaction saveTransaction(Transaction transaction) {
        TransactionDataEntity transactionDataEntity = mapperObjects.mapToTransactionDataEntity(transaction);
        TransactionDataEntity savedTransactionDataEntity = repository.save(transactionDataEntity);

        Account account = mapperObjects.mapToAccount(savedTransactionDataEntity.getAccount());
        transaction.setAccount(account);

        return transaction;
    }
}

