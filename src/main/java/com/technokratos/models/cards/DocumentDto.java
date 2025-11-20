package com.technokratos.models.cards;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDto {
    private UUID id;
    private String docType;
    private LocalDateTime createdDate;
    private UUID userId;
    private String userFio;
    private String cardNumber;
}
