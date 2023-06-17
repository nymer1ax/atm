package co.com.atm.jpa.account;

import co.com.atm.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface AccountDataRepository extends JpaRepository<AccountDataEntity, Long>, QueryByExampleExecutor<AccountDataEntity> {
}
