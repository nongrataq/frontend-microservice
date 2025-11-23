package com.technokratos.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technokratos.models.ApiResponse;
import com.technokratos.models.contracts.BalanceDto;
import com.technokratos.models.contracts.ContractDto;
import com.technokratos.models.contracts.CreateTransferRequest;
import com.technokratos.models.contracts.TransactionDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.*;

import java.util.List;

@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    private final String BASE_URL = "http://localhost:8083/api/transactions";
    private final String GET_BALANCE = BASE_URL + "/balance?contractName=%s";
    private final String CREATE_TRANSACTION = BASE_URL + "/create";

    @SneakyThrows
    @Override
    public ApiResponse<BalanceDto> getBalance(String contractName) {

        Request request = new Request.Builder()
                .url(GET_BALANCE.formatted(contractName))
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            return objectMapper.readValue(response.body().string(), new TypeReference<ApiResponse<BalanceDto>>() {
            });
        }
    }

    @SneakyThrows
    @Override
    public ApiResponse<TransactionDto> createTransfer(CreateTransferRequest transferRequest) {

        String jsonTransferRequest = objectMapper.writeValueAsString(transferRequest);

        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json"),
                jsonTransferRequest
        );

        Request request = new Request.Builder()
                .post(requestBody)
                .url(CREATE_TRANSACTION)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return objectMapper.readValue(response.body().string(), new TypeReference<>() {});
        }
    }

    @Override
    public ApiResponse<List<TransactionDto>> getTransactions(String contractName) {
        return null;
    }

    @Override
    public ApiResponse<ContractDto> createContract(String contractName) {
        return null;
    }
}

