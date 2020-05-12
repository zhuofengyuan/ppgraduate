package com.fengtoos.ppgraduate.auth.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FengtoosException extends RuntimeException {

    private Integer code;
    private String message;

    public FengtoosException(String message){
        this.message = message;
        this.code = 500;
    }
}
