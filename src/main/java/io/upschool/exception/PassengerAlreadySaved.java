package io.upschool.exception;

public class PassengerAlreadySaved extends RuntimeException {

    public PassengerAlreadySaved(String message) {
        super(message);
    }
    public PassengerAlreadySaved(String message, Throwable cause) {
        super(message, cause);
    }
}
