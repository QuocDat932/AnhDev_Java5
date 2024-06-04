package com.quocdat.java5.api;

import com.nimbusds.jose.JOSEException;
import com.quocdat.java5.data.dto.request.AuthenticationRequestDto;
import com.quocdat.java5.data.dto.request.IntrospectRequest;
import com.quocdat.java5.data.dto.response.ApiResponse;
import com.quocdat.java5.data.dto.response.AuthenticationResponseDto;
import com.quocdat.java5.data.dto.response.IntrospectResponse;
import com.quocdat.java5.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationApi {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponseDto> authenticate(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        ApiResponse<AuthenticationResponseDto> result = new ApiResponse();
        try {
            result.setSuccess(true);
            result.setPayload(authenticationService.authenticate(authenticationRequestDto));
        } catch (Exception e) {
            result.setSuccess(false);
            result.setError(e.getMessage());
        }
        return result;
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
       var result = authenticationService.introspect(request);
       return ApiResponse.<IntrospectResponse>builder()
               .success(true)
               .payload(result)
               .build();
    }
}
