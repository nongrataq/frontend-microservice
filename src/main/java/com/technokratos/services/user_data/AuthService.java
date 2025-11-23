package com.technokratos.services.user_data;

import com.technokratos.models.users.RequestSignInUserDto;
import com.technokratos.models.users.RequestSignUpUserDto;
import com.technokratos.models.users.ResponseSignInUserDto;
import com.technokratos.models.users.ResponseUserDto;

import java.util.UUID;


public interface AuthService {

    ResponseSignInUserDto signIn(RequestSignInUserDto requestSignInUserDto);

    ResponseUserDto signUp(RequestSignUpUserDto dto);

    ResponseUserDto validateToken(UUID token);
}
