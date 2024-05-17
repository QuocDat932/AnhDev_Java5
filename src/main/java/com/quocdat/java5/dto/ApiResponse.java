package com.quocdat.java5.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private int status;
    private boolean success;
    private T payload;
    private String message;
    private Object error;
    private String id;
}
