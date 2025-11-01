package com.technokratos.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technokratos.models.RequestSignInUserDto;
import com.technokratos.models.ResponseSignInUserDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.*;

@RequiredArgsConstructor
public class SignInService {

    private final OkHttpClient client;
    ObjectMapper objectMapper;

    @SneakyThrows
    public ResponseSignInUserDto signIn(RequestSignInUserDto requestSignInUserDto) {
        String json = objectMapper.writeValueAsString(requestSignInUserDto);

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
                return objectMapper.readValue(resp.body().string(), ResponseSignInUserDto.class);
            } else {
                throw new RuntimeException(resp.message());
            }
        }
    }
}
