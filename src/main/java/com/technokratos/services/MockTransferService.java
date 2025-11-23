package com.technokratos.services;

import com.technokratos.models.ApiResponse;
import com.technokratos.models.contracts.BalanceDto;
import com.technokratos.models.contracts.ContractDto;
import com.technokratos.models.contracts.CreateTransferRequest;
import com.technokratos.models.contracts.TransactionDto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class MockTransferService implements TransferService {

    @Override
    public ApiResponse<BalanceDto> getBalance(String contractName) {
        return ApiResponse.<BalanceDto>builder()
                .message("success")
                .data(new BalanceDto(contractName, new BigDecimal("50000.75")))
                .build();
    }

    @Override
    public ApiResponse<TransactionDto> createTransfer(CreateTransferRequest transferRequest) {
        UUID transactionId = UUID.randomUUID();
        UUID sourceId = UUID.fromString("11111111-1111-1111-1111-111111111111");
        UUID targetId = UUID.fromString("22222222-2222-2222-2222-222222222222");
        
        return ApiResponse.<TransactionDto>builder()
                .message("success")
                .data(new TransactionDto(
                        transactionId,
                        sourceId,
                        targetId,
                        new BigDecimal("1500.00"),
                        "Mock transfer transaction",
                        Instant.now()
                ))
                .build();
    }

    @Override
    public ApiResponse<List<TransactionDto>> getTransactions(String contractName) {
        UUID contractId = UUID.fromString("33333333-3333-3333-3333-333333333333");
        List<TransactionDto> transactions = List.of(
                new TransactionDto(
                        UUID.randomUUID(),
                        contractId,
                        UUID.fromString("44444444-4444-4444-4444-444444444444"),
                        new BigDecimal("1000.00"),
                        "Payment for services",
                        Instant.now().minusSeconds(3600)
                ),
                new TransactionDto(
                        UUID.randomUUID(),
                        UUID.fromString("55555555-5555-5555-5555-555555555555"),
                        contractId,
                        new BigDecimal("2500.50"),
                        "Salary transfer",
                        Instant.now().minusSeconds(7200)
                )
        );
        
        return ApiResponse.<List<TransactionDto>>builder()
                .message("success")
                .data(transactions)
                .build();
    }

    @Override
    public ApiResponse<ContractDto> createContract(String contractName) {
        return ApiResponse.<ContractDto>builder()
                .message("success")
                .data(new ContractDto())
                .build();
    }
}
