package com.technokratos.models.cards;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private UUID userId;
    private UUID cardProductId;
    private String plasticName;
    private String expDate;
    private String contractName;
    private String cardName;
    private DocumentDto openDocument;
    private DocumentDto closeDocument;
    private boolean closeFlag;
}
