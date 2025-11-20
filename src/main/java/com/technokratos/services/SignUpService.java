package com.technokratos.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technokratos.models.FieldErrorDto;
import com.technokratos.models.RequestSignUpUserDto;
import com.technokratos.models.ResponseSignUpUserDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.*;

import java.util.List;

@RequiredArgsConstructor
public class SignUpService {

    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public ResponseSignUpUserDto signUp(RequestSignUpUserDto dto) {
        String json = objectMapper.writeValueAsString(dto);

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                json
        );

        Request request = new Request.Builder()
                .url("http://localhost:8080/api/user-data/sign-up")
                .post(body)
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return objectMapper.readValue(response.body().string(), ResponseSignUpUserDto.class);
            } else {
                return ResponseSignUpUserDto.builder()
                        .success(false)
                        .errors(List.of(
                                FieldErrorDto.builder()
                                        .field("system")
                                        .message("Ошибка регистрации: %s".formatted(response.code()))
                                        .build()
                        ))
                        .build();
            }
        }

    }
}
