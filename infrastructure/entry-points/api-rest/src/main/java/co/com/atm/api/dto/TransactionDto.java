package co.com.atm.api.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class TransactionDto {
    private final Long accountId;
    private final BigDecimal amount;
}
