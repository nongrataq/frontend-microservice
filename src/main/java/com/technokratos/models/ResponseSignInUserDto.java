package com.technokratos.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSignInUserDto {

    private UUID userId;

    private boolean success;

    private List<FieldErrorDto> errors;

    private String token;
}
