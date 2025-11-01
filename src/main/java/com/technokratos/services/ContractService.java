package com.technokratos.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class ContractService {

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public Map<String, Object> createContract(Map<String, Object> contractName, BigDecimal balance) throws JsonProcessingException {
        Map<String, Object> response = Map.of(
                "contractName", contractName,
                "balance", balance
        );

        String json = objectMapper.writeValueAsString(response);

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), json);

        Request request = new Request.Builder()
                .url("http://localhost:8080/api/transactions/contract")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();


        try (Response httpResponse = client.newCall(request).execute()) {
            if (httpResponse.isSuccessful() && httpResponse.body() != null) {
                String responseJson = httpResponse.body().string();
                return objectMapper.readValue(responseJson, new TypeReference<Map<String, Object>>() {
                });
            } else {
                throw new RuntimeException("Error" + httpResponse.code());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


