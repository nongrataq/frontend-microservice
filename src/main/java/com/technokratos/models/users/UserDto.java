package com.technokratos.models.users;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserDto (
        UUID id,
        String phone,
        String fio
) { }
