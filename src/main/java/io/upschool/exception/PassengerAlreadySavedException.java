package io.upschool.exception;

public class PassengerAlreadySavedException extends RuntimeException {

    public PassengerAlreadySavedException(String message) {
        super(message);
    }
    public PassengerAlreadySavedException(String message, Throwable cause) {
        super(message, cause);
    }
}
