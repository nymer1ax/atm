package co.com.atm.jpa.account;

import co.com.atm.jpa.helper.AdapterOperations;
import co.com.atm.model.account.Account;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AccountAdapterRepository extends AdapterOperations<Account, AccountDataEntity, Long, AccountDataRepository> {

    public AccountAdapterRepository(AccountDataRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Account.class/* change for domain model */));

    }
}
