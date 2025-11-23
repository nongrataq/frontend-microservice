package com.technokratos.models.cards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardDto {
    private UUID id;
    private UUID userId;
    private UUID cardProductId;
    private String plasticName;
    private String cardImageLink;
    private String expDate;
    private String contractName;
    private String cardName;
    private UUID openDocumentId;
    private UUID closeDocumentId;
    private Integer cvv;
    private boolean closeFlag;
}
