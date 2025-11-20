package com.technokratos.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technokratos.models.FieldErrorDto;
import com.technokratos.models.RequestSignInUserDto;
import com.technokratos.models.ResponseSignInUserDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class SignInService {

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public ResponseSignInUserDto signIn(RequestSignInUserDto requestSignInUserDto) {
        String json = objectMapper.writeValueAsString(requestSignInUserDto);

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"),
                json
        );

        Request req = new Request.Builder()
                .url("http://localhost:8080/api/user-data/sign-in")
                .post(body)
                .build();

        log.info(String.valueOf(req));

        try (Response resp = client.newCall(req).execute()) {
            if (resp.isSuccessful()) {
                return objectMapper.readValue(resp.body().string(), ResponseSignInUserDto.class);
            } else {
                return ResponseSignInUserDto.builder()
                        .success(false)
                        .errors(List.of(
                                FieldErrorDto.builder()
                                        .field("system")
                                        .message("Ошибка входа: %s".formatted(resp.code()))
                                        .build()
                        ))
                        .build();
            }
        }
    }
}
