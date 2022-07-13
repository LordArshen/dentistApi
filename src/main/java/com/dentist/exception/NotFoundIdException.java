package com.dentist.exception;

public class NotFoundIdException extends RuntimeException{

    public NotFoundIdException(String message) {
        super(message);
    }

    public NotFoundIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
