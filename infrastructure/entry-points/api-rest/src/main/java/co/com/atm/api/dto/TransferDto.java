package co.com.atm.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class TransferDto {
    private final Long sourceAccountId;

    private final Long destinationAccountId;

    private final BigDecimal amount;
}
