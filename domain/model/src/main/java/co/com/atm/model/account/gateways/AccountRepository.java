package co.com.atm.model.account.gateways;

import co.com.atm.model.account.Account;
import java.util.Optional;

public interface AccountRepository {
    Optional<Account> findById(Long accountId);
    Account save(Account account);
}
