package com.quocdat.java5.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.quocdat.java5.data.dto.request.AuthenticationRequestDto;
import com.quocdat.java5.data.dto.request.IntrospectRequest;
import com.quocdat.java5.data.dto.response.AuthenticationResponseDto;
import com.quocdat.java5.data.dto.response.IntrospectResponse;
import com.quocdat.java5.exception.AppException;
import com.quocdat.java5.exception.ErrorCode;
import com.quocdat.java5.repository.AccountRepo;
import com.quocdat.java5.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AccountRepo accountRepo;

    @NonFinal
    @Value("${jwt.signer-key}")
    protected String SECRET_KEY;

    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto requestDto) throws AppException {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        var user = accountRepo.findAccountByTk(requestDto.getUsername()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        boolean authenticated = passwordEncoder.matches(requestDto.getPassword(), user.getMk());
        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        var token = generateToken(requestDto.getUsername());
        return AuthenticationResponseDto.builder().token(token).authenticated(true).build();
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException, AppException {
        var token = request.getToken();

        JWSVerifier jwsVerifier = new MACVerifier(SECRET_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        var verified = signedJWT.verify(jwsVerifier);

        Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime();

        return IntrospectResponse.builder()
                .valid(verified && expiration.after(new Date()))
                .build();
    }

    private String generateToken(String username) {
        JWSHeader jweHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder().subject(username).issueTime(new Date()).expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli())).build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jweHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot sign JWT", e);
            throw new RuntimeException(e);
        }
    }
}
