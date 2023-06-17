package co.com.atm.jpa.account;

import co.com.atm.jpa.helper.AdapterOperations;
import co.com.atm.model.account.Account;
import co.com.atm.model.account.gateways.AccountRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountAdapterRepository extends AdapterOperations<Account, AccountDataEntity, Long, AccountDataRepository> implements AccountRepository   {

    public AccountAdapterRepository(AccountDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Account.class/* change for domain model */));

    }

    @Override
    public Optional<Account> findAccountById(Long accountId) {
        return Optional.empty();
    }

    @Override
    public Account saveAccount(Account account) {
        return null;
    }

    @Override
    public List<Account> findAllOtherAccounts(Long id) {
        return null;
    }

    @Override
    public List<Account> findAllAccountByIdIn(List<Account> otherAccounts) {
        return null;
    }

    @Override
    public List<Account> findAllAccounts() {
        return null;
    }
}
