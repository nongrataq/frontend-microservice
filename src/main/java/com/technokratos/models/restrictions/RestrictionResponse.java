package com.technokratos.models.restrictions;

import lombok.Data;

import java.util.UUID;

//"message": "success",
//  "data": {
//    "id": "123e4567-e89b-12d3-a456-426614174000",
//    "userId": "123e4567-e89b-12d3-a456-426614174001",
//    "blockType": "PARTICULAR",
//    "isActive": true
//  }
@Data
public class RestrictionResponse {
    private UUID restrictionId;
    private UUID userId;
    private BlockType blockType;
    private boolean isActive;
}
