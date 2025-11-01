package com.technokratos.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSignInUserDto {

    private boolean success;

    private List<FieldErrorDto> errors;

    private String token;
}
