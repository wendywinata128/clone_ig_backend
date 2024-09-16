package com.commsult_test.clone_ig.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseException extends Exception {
    private int status;
    private Object error;

    public ResponseException(String message, int status, Object error){
        super(message);
        this.status = status;
        this.error = error;
    }
}
