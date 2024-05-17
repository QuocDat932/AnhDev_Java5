package com.quocdat.java5.exception;

public enum ErrorCode {
    STUDENT_EXISTED(1001, "Student existed"),
    STUDENT_NOT_EXIST(1002, "Student not exist")
    ;

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
