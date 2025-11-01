package com.technokratos.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technokratos.models.ResponseSignInUserDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.*;

@RequiredArgsConstructor
public class TokenService {

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public boolean isTokenAlive(String token) {
        String json = objectMapper.writeValueAsString(token);

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"),
                json
        );

        Request req = new Request.Builder()
                .url("http://localhost:8080/api/user-data")
                .post(body)
                .build();

        try (Response resp = client.newCall(req).execute()) {
            if (resp.isSuccessful()) {
                return objectMapper.readValue(resp.body().string(), Boolean.class);
            } else {
                throw new RuntimeException(resp.message());
            }
        }
    }
}
