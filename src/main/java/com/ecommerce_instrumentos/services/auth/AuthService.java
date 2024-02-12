package com.ecommerce_instrumentos.services.auth;

import com.ecommerce_instrumentos.dto.SignupRequest;
import com.ecommerce_instrumentos.dto.UserDto;

public interface AuthService {

    UserDto createUser(SignupRequest signupRequest);

    Boolean hasUserWithEmail(String email);

}
