package com.sample.server.auth.service;

import com.sample.server.auth.dao.request.SignUpRequest;
import com.sample.server.auth.dao.request.SigninRequest;
import com.sample.server.auth.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
