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
public class CreateCardRequest {
    private UUID userId;
    private UUID cardProductId;
    private String contractName;
    private String cardName;
}