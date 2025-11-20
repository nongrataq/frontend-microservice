package com.technokratos.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technokratos.models.BlockType;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
public class UserRestrictionsService {

    private ObjectMapper objectMapper;
    private OkHttpClient client;

    private final String CHECK_BLOCK_API_ENDPOINT = "http://localhost:8080/api/user-restrictions/check-block?userId=%s";

    public BlockType checkRestriction(UUID userId) {

        Request request = new Request.Builder()
                .get()
                .url(CHECK_BLOCK_API_ENDPOINT.formatted(userId))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                return objectMapper.readValue(responseBody, BlockType.class);
            }

        } catch (IOException e) {

        }
        return null;
    }
}
