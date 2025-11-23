package com.technokratos.services.cards;

import com.technokratos.models.ApiResponse;
import com.technokratos.models.cards.CardDto;
import com.technokratos.models.cards.CardProductDto;
import com.technokratos.models.cards.CreateCardRequest;
import com.technokratos.models.cards.DocType;
import com.technokratos.models.cards.DocumentDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class MockCardServiceImpl implements CardService {
    @Override
    public ApiResponse<List<CardDto>> getUserCards(UUID userId) {
        DocumentDto openDoc = DocumentDto.builder()
                .id(UUID.randomUUID())
                .docType(DocType.OPEN)
                .createdDate(LocalDateTime.now().minusMonths(2))
                .userId(userId)
                .userFio("Иван Иванов")
                .cardNumber("1234 5678 9012 3456")
                .build();

        CardDto card1 = CardDto.builder()
                .userId(userId)
                .cardProductId(UUID.fromString("11111111-1111-1111-1111-111111111111"))
                .plasticName("Visa Classic")
                .expDate("12/26")
                .cardImageLink("https://avatars.mds.yandex.net/i?id=57e678835ec55aed8048e39a4abcc93c7f4ff773-7452498-images-thumbs&n=13")
                .contractName("CONTRACT-001")
                .cardName("Основная карта")
                .openDocumentId(UUID.randomUUID())
                .closeDocumentId(UUID.randomUUID())
                .closeFlag(false)
                .build();

        CardDto card2 = CardDto.builder()
                .userId(userId)
                .cardProductId(UUID.fromString("22222222-2222-2222-2222-222222222222"))
                .plasticName("MasterCard Gold")
                .expDate("06/27")
                .contractName("CONTRACT-002")
                .cardImageLink("https://avatars.mds.yandex.net/i?id=a2a55b0f33135f2476b533bfa675e933_l-9289605-images-thumbs&n=13")
                .cardName("Зарплатная карта")
                .openDocumentId(UUID.randomUUID())
                .closeDocumentId(UUID.randomUUID())
                .closeFlag(false)
                .build();

        return ApiResponse.<List<CardDto>>builder()
                .message("success")
                .data(List.of(card1, card2))
                .build();
    }

    @Override
    public ApiResponse<CardDto> orderCard(CreateCardRequest cardRequest) {
        DocumentDto openDoc = DocumentDto.builder()
                .id(UUID.randomUUID())
                .docType(DocType.OPEN)
                .createdDate(LocalDateTime.now())
                .userId(cardRequest.getUserId())
                .userFio("Иван Иванов")
                .cardNumber("9999 8888 7777 6666")
                .build();

        CardDto card = CardDto.builder()
                .userId(cardRequest.getUserId())
                .cardProductId(cardRequest.getCardProductId())
                .plasticName("Visa Classic")
                .expDate("12/28")
                .contractName(cardRequest.getContractName())
                .cardName(cardRequest.getCardName())
                .openDocumentId(UUID.randomUUID())
                .closeDocumentId(UUID.randomUUID())
                .closeFlag(false)
                .build();

        return ApiResponse.<CardDto>builder()
                .message("success")
                .data(card)
                .build();
    }

    @Override
    public ApiResponse<List<CardProductDto>> getAllCardsForChoose() {
        CardProductDto product1 = CardProductDto.builder()
                .id(UUID.fromString("11111111-1111-1111-1111-111111111111"))
                .cardName("Visa Classic")
                .description("Классическая карта Visa для ежедневных покупок")
                .cardImageLink("https://example.com/images/visa-classic.png")
                .build();

        CardProductDto product2 = CardProductDto.builder()
                .id(UUID.fromString("22222222-2222-2222-2222-222222222222"))
                .cardName("MasterCard Gold")
                .description("Золотая карта MasterCard с расширенными привилегиями")
                .cardImageLink("https://example.com/images/mastercard-gold.png")
                .build();

        CardProductDto product3 = CardProductDto.builder()
                .id(UUID.fromString("33333333-3333-3333-3333-333333333333"))
                .cardName("Visa Platinum")
                .description("Платиновая карта Visa с максимальными преимуществами")
                .cardImageLink("https://example.com/images/visa-platinum.png")
                .build();

        return ApiResponse.<List<CardProductDto>>builder()
                .message("success")
                .data(List.of(product1, product2, product3))
                .build();
    }

    @Override
    public ApiResponse<CardDto> getCardInfo(UUID cardId) {
        return null;
    }

    @Override
    public ApiResponse<Boolean> closeCard(UUID cardId) {
        return null;
    }
}
