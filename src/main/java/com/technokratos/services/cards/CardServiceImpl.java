package com.technokratos.services.cards;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technokratos.models.ApiResponse;
import com.technokratos.models.cards.CardDto;
import com.technokratos.models.cards.CardProductDto;
import com.technokratos.models.cards.CreateCardRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.*;
import okio.ByteString;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    private final String BASE_URL = "http://26.125.164.47:8085/api/cards";
    private final String GET_ALL_CARDS_FOR_CHOOSE = BASE_URL + "/card-products";
    private final String ORDER_CARD = BASE_URL + "/create-card";
    private final String GET_USER_CARDS = BASE_URL + "/users/%s";
    private final String GET_ALL_INFO_WITH_DOCUMENTS = BASE_URL + "/get-all-info?id=%s";
    private final String GET_INFO = BASE_URL + "/get-info?id=%s";
    private final String CLOSE_CARD = BASE_URL + "/close-card?id=%s";


    @Override
    @SneakyThrows
    public ApiResponse<List<CardDto>> getUserCards(UUID userId) {

        Request request = new Request.Builder()
                .get()
                .url(GET_USER_CARDS.formatted(userId))
                .build();

        try (Response response = client.newCall(request).execute()) {
            return objectMapper.readValue(
                    response.body().string(),
                    new TypeReference<>() {}
            );
        }
    }

    @Override
    @SneakyThrows
    public ApiResponse<CardDto> orderCard(CreateCardRequest cardRequest) {

        String jsonCard = objectMapper.writeValueAsString(cardRequest);

        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                jsonCard
        );

        Request request = new Request.Builder()
                .url(ORDER_CARD)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.code() == 201) {
                return objectMapper.readValue(
                        response.body().string(),
                        new TypeReference<>() {
                        }
                );
            }
            throw new RuntimeException("Ошибка создания карты: " + response.body().string());

        }
    }

    @Override
    @SneakyThrows
    public ApiResponse<List<CardProductDto>> getAllCardsForChoose() {

        Request request = new Request.Builder()
                .get()
                .url(GET_ALL_CARDS_FOR_CHOOSE)
                .build();

        try (Response response = client.newCall(request).execute()) {

            if (response.isSuccessful()) {
                return objectMapper.readValue(
                        response.body().string(),
                        new TypeReference<>() {}
                );
            }
            throw new RuntimeException("Ошибка получения карт" + response.body().string());
        }
    }

    @Override
    @SneakyThrows
    public ApiResponse<CardDto> getCardInfo(UUID cardId) {

        Request request = new Request.Builder()
                .url(GET_INFO.formatted(cardId))
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            return objectMapper.readValue(response.body().string(), new TypeReference<>() {
            });
        }

    }

    @SneakyThrows
    @Override
    public ApiResponse<Boolean> closeCard(UUID cardId) {

        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=UTF-8"),
                ByteString.EMPTY
        );

        Request request = new Request.Builder()
                .post(requestBody)
                .url(CLOSE_CARD.formatted(cardId))
                .build();

        try (Response response = client.newCall(request).execute()) {
            String resp = response.body().string();
            return objectMapper.readValue(resp, new TypeReference<>() {});
        }

    }


}
