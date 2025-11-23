package com.technokratos.services.cards;

import com.technokratos.models.ApiResponse;
import com.technokratos.models.cards.CardDto;
import com.technokratos.models.cards.CardProductDto;
import com.technokratos.models.cards.CreateCardRequest;

import java.util.List;
import java.util.UUID;

public interface CardService {
    ApiResponse<List<CardDto>> getUserCards(UUID userId);

    ApiResponse<CardDto> orderCard(CreateCardRequest cardRequest);

    ApiResponse<List<CardProductDto>> getAllCardsForChoose();

    ApiResponse<CardDto> getCardInfo(UUID cardId);

    ApiResponse<Boolean> closeCard(UUID cardId);
}
