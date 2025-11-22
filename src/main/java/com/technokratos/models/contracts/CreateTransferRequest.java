package com.technokratos.models.contracts;

import java.math.BigDecimal;

public class CreateTransferRequest {
    private String sourseContractId;
    private String targetContractId;
    private BigDecimal amount;
    private String description;
}
