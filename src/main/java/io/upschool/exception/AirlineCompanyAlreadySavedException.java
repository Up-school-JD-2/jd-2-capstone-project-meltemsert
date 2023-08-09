package io.upschool.exception;

public class AirlineCompanyAlreadySavedException extends RuntimeException{

    public AirlineCompanyAlreadySavedException(String message) {
        super(message);
    }
    public AirlineCompanyAlreadySavedException(String message, Throwable cause) {
        super(message, cause);
    }

}
