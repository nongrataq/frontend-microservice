package com.technokratos.models.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserDto {
    private UserDto user;
    boolean success;
    List<FieldErrorDto> errors;
}
