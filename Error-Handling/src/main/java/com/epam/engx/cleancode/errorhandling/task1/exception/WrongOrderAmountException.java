package com.epam.engx.cleancode.errorhandling.task1.exception;

public class WrongOrderAmountException extends RuntimeException{

    public WrongOrderAmountException() {
        super();
    }

    public WrongOrderAmountException(String message) {
        super(message);
    }

    public WrongOrderAmountException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongOrderAmountException(Throwable cause) {
        super(cause);
    }
}
