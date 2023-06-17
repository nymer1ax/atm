package co.com.atm.model.transaction;
import co.com.atm.model.account.Account;
import co.com.atm.model.transactiontype.TransactionType;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Transaction {
    private Long id;
    private TransactionType transactionType;
    private BigDecimal amount;
    private BigDecimal finalBalance;
    private Account account;
}
