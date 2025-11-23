package com.technokratos.services.user_data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technokratos.models.users.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.util.List;
import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final String BASE_ENDPOINT = "http://26.166.200.60:8080/api";
    private final String USER_DATA_SIGN_UP_ENDPOINT = BASE_ENDPOINT + "/users";
    private final String USER_DATA_SIGN_IN_ENDPOINT = BASE_ENDPOINT + "/token";
    private final String USER_DATA_VALIDATE_TOKEN = BASE_ENDPOINT + "/token/validate";

    private final ObjectMapper objectMapper;
    private final OkHttpClient client;

    @SneakyThrows
    @Override
    public ResponseSignInUserDto signIn(RequestSignInUserDto requestSignInUserDto) {
        String json = objectMapper.writeValueAsString(requestSignInUserDto);

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                json
        );

        Request req = new Request.Builder()
                .url(USER_DATA_SIGN_IN_ENDPOINT)
                .post(body)
                .build();

        try (Response resp = client.newCall(req).execute()) {
            String responseBody = resp.body().string();
            log.info("Response code: {}", resp.code());
            log.info("Response body: {}", responseBody);

            if (resp.isSuccessful()) {
                // Только при успешном ответе (201 Created) парсим JSON
                return objectMapper.readValue(responseBody, ResponseSignInUserDto.class);
            } else {
                // При ошибках (404, 400, 500) тоже парсим JSON, но логируем ошибку
                try {
                    ResponseSignInUserDto errorResponse = objectMapper.readValue(responseBody, ResponseSignInUserDto.class);
                    log.error("Sign in failed with status {}: {}", resp.code(), errorResponse);
                    return errorResponse;
                } catch (Exception e) {
                    // Если не удалось распарсить ошибку, создаем стандартную
                    log.error("Failed to parse error response: {}", responseBody);
                    return ResponseSignInUserDto.builder()
                            .success(false)
                            .errors(List.of(FieldErrorDto.builder()
                                    .type("SYSTEM_ERROR")
                                    .message("Authentication service unavailable")
                                    .build()))
                            .build();
                }
            }
        }
    }

    @SneakyThrows
    @Override
    public ResponseUserDto signUp(RequestSignUpUserDto dto) {

        String userJson = objectMapper.writeValueAsString(dto);

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                userJson
        );

        Request request = new Request.Builder()
                .url(USER_DATA_SIGN_UP_ENDPOINT)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            ResponseUserDto responseUserDto = objectMapper.readValue(response.body().string(), ResponseUserDto.class);
            log.info(responseUserDto.toString());
            return responseUserDto;
        }

    }

    @SneakyThrows
    @Override
    public ResponseUserDto validateToken(UUID token) {

        String jsonToken = "{ \"token\" : \"%s\" }".formatted(token.toString());

        RequestBody responseBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                jsonToken
        );

        Request request = new Request.Builder()
                .url(USER_DATA_VALIDATE_TOKEN)
                .post(responseBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return objectMapper.readValue(response.body().string(), ResponseUserDto.class);
        }
    }
}
