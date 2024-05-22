package com.quocdat.java5.service;

import com.nimbusds.jose.JOSEException;
import com.quocdat.java5.dto.request.AuthenticationRequestDto;
import com.quocdat.java5.dto.request.IntrospectRequest;
import com.quocdat.java5.dto.response.AuthenticationResponseDto;
import com.quocdat.java5.dto.response.IntrospectResponse;
import com.quocdat.java5.exception.AppException;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto) throws AppException;
    IntrospectResponse introspect(IntrospectRequest request) throws AppException, JOSEException, ParseException;
}
