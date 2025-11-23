package com.technokratos.services;

import com.technokratos.models.ApiResponse;
import com.technokratos.models.restrictions.RestrictionResponse;

import java.util.UUID;

public interface UserRestrictionsService {
    ApiResponse<RestrictionResponse> checkRestriction(UUID userId);
}
