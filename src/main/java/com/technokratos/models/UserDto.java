package com.technokratos.models;

import java.util.UUID;

public record UserDto (
        UUID id,
        String phone,
        String fio
) {

}
