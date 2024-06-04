package com.quocdat.java5.service;

import com.nimbusds.jose.JOSEException;
import com.quocdat.java5.data.dto.request.AuthenticationRequestDto;
import com.quocdat.java5.data.dto.request.IntrospectRequest;
import com.quocdat.java5.data.dto.response.AuthenticationResponseDto;
import com.quocdat.java5.data.dto.response.IntrospectResponse;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto) throws Exception;
    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;
}
