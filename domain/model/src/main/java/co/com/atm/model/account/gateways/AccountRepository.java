package co.com.atm.model.account.gateways;

import co.com.atm.model.account.Account;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Optional<Account> findById(Long accountId);
    Account save(Account account);

    List<Account> findAllOtherAccounts(Long id);

    List<Account> findAllByIn(List<Account> otherAccounts);

    List<Account> findAll();
}
