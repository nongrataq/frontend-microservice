package com.technokratos.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSignUpUserDto {
    boolean success;
    List<FieldErrorDto> errors;
}
