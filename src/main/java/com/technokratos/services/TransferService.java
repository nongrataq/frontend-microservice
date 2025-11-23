package com.technokratos.services;

import com.technokratos.models.ApiResponse;
import com.technokratos.models.contracts.BalanceDto;
import com.technokratos.models.contracts.ContractDto;
import com.technokratos.models.contracts.CreateTransferRequest;
import com.technokratos.models.contracts.TransactionDto;

import java.util.List;

public interface TransferService {
    ApiResponse<BalanceDto> getBalance(String contractName);
    ApiResponse<TransactionDto> createTransfer(CreateTransferRequest transferRequest);
    ApiResponse<List<TransactionDto>> getTransactions(String contractName);
    ApiResponse<ContractDto> createContract(String contractName);
}
