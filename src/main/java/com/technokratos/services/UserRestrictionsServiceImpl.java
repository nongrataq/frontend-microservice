package com.technokratos.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technokratos.models.ApiResponse;
import com.technokratos.models.restrictions.RestrictionResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.UUID;

@RequiredArgsConstructor
public class UserRestrictionsServiceImpl implements UserRestrictionsService {

    private final ObjectMapper objectMapper;
    private final OkHttpClient client;

    private final String CHECK_BLOCK_API_ENDPOINT = "http://localhost:8080/api/restrictions?userId=%s";

    @SneakyThrows
    @Override
    public ApiResponse<RestrictionResponse> checkRestriction(UUID userId) {

        Request request = new Request.Builder()
                .get()
                .url(CHECK_BLOCK_API_ENDPOINT.formatted(userId))
                .build();

        try (Response response = client.newCall(request).execute()) {

            String responseBody = response.body().string();
            return objectMapper.readValue(responseBody, new TypeReference<>() {});
        }
    }
}

