package com.epam.engx.cleancode.errorhandling.task1.exception;

public class DaoIsNullException extends RuntimeException{

    public DaoIsNullException() {
        super();
    }

    public DaoIsNullException(String message) {
        super(message);
    }

    public DaoIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoIsNullException(Throwable cause) {
        super(cause);
    }
}
