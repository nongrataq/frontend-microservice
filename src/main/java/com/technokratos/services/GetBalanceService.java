package com.technokratos.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
public class GetBalanceService {

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public Map<String, Object> getBalance(String conctractName) {
        Request request = new Request.Builder()
                .url("http://localhost:8080/transfer-balance?contractName=" + conctractName)
                .get()
                .addHeader("Accept", "application/json")
                .build();
        try (Response HttpResponse = client.newCall(request).execute()) {
            if (HttpResponse.isSuccessful()) {
                return objectMapper.readValue(HttpResponse.body().string(), new TypeReference<Map<String, Object>>() {});
            } else {
                throw new RuntimeException("Error get balance: " +HttpResponse.code());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
