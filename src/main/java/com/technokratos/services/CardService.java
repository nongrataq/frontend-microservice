package com.technokratos.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technokratos.models.cards.ApiResponse;
import com.technokratos.models.cards.CardDto;
import com.technokratos.models.cards.CardProductDto;
import com.technokratos.models.cards.CreateCardRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class CardService {

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    private final String BASE_URL = "http://localhost:8082/api/cards";
    private final String GET_ALL_CARDS_FOR_CHOOSE = BASE_URL + "/card-products";
    private final String ORDER_CARD = BASE_URL + "/create-card";
    private final String GET_USER_CARDS = BASE_URL + "/users/%s";


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


}
