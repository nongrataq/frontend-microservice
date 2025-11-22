package com.technokratos.models.contracts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private UUID transactionId;
    private UUID sourceContractId;
    private UUID targetContractId;
    private BigDecimal amount;
    private String description;
    private Instant createdDate;
}
