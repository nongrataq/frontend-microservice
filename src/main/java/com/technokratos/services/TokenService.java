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
        Request req = new Request.Builder()
                .url("http://localhost:8080/api/user-data/tokens/validate?token=%s".formatted(token))
                .get()
                .build();

        try (Response resp = client.newCall(req).execute()) {
            return resp.isSuccessful(); //200 - валидный токен, 4xx - невалидный
        }
    }
}
