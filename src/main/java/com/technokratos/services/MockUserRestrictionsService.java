package com.technokratos.services;

import com.technokratos.models.ApiResponse;
import com.technokratos.models.restrictions.BlockType;
import com.technokratos.models.restrictions.RestrictionResponse;

import java.util.UUID;

public class MockUserRestrictionsService implements UserRestrictionsService {

    @Override
    public ApiResponse<RestrictionResponse> checkRestriction(UUID userId) {
        RestrictionResponse restrictionResponse = new RestrictionResponse();
        restrictionResponse.setRestrictionId(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"));
        restrictionResponse.setUserId(userId);
        restrictionResponse.setBlockType(BlockType.PARTICULAR);
        restrictionResponse.setActive(false);
        
        return ApiResponse.<RestrictionResponse>builder()
                .message("success")
                .data(restrictionResponse)
                .build();
    }
}
