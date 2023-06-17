package co.com.atm.jpa.account;

import co.com.atm.jpa.transaction.TransactionDataEntity;
import co.com.atm.model.transaction.Transaction;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AccountDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;

    @Column(precision = 19, scale = 4) // Define precision and scale for BigDecimal
    private BigDecimal balance;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<TransactionDataEntity> transactionHistory = new ArrayList<>();

}
