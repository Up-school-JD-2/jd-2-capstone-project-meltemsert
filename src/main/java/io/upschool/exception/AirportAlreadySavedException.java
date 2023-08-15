package io.upschool.exception;

public class AirportAlreadySavedException extends RuntimeException {
    public AirportAlreadySavedException(String message) {
        super(message);
    }
}
