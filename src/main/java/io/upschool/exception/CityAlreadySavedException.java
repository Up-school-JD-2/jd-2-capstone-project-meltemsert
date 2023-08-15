package io.upschool.exception;

public class CityAlreadySavedException extends RuntimeException{

    public CityAlreadySavedException(String message) {
        super(message);
    }
}
