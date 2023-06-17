package co.com.atm.model.account;
import co.com.atm.model.transaction.Transaction;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Account {
    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private List<Transaction> transactionHistory;
}
