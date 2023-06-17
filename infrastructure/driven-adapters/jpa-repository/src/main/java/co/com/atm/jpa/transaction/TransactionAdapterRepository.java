package co.com.atm.jpa.transaction;

import co.com.atm.jpa.helper.AdapterOperations;
import co.com.atm.model.transaction.Transaction;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionAdapterRepository extends AdapterOperations<Transaction, TransactionDataEntity, Long, TransactionDataRepository> {
    public TransactionAdapterRepository(TransactionDataRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Transaction.class/* change for domain model */));
    }
}

