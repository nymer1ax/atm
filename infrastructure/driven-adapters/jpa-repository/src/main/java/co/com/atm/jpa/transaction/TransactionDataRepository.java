package co.com.atm.jpa.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface TransactionDataRepository extends JpaRepository<TransactionDataEntity, Long>, QueryByExampleExecutor<TransactionDataEntity> {
}
