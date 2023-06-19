package co.com.atm.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class AccountDto {
    private String accountNumber;
    private BigDecimal balance;
}