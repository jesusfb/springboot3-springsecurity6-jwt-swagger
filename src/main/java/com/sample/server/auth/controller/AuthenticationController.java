package com.sample.server.auth.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sample.server.auth.dao.request.SignUpRequest;
import com.sample.server.auth.dao.request.SigninRequest;
import com.sample.server.auth.dao.response.JwtAuthenticationResponse;
import com.sample.server.auth.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

import java.util.Base64;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    @SecurityRequirements
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }
    @PostMapping("/signin")
    @SecurityRequirements
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody(required = false) SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

}
