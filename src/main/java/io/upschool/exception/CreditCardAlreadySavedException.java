package io.upschool.exception;

public class CreditCardAlreadySavedException extends RuntimeException {
    public CreditCardAlreadySavedException(String message) {
        super(message);
    }

    public CreditCardAlreadySavedException(String message, Throwable cause) {
        super(message, cause);
    }
}
