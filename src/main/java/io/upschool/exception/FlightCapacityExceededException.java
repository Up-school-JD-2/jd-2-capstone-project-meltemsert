package io.upschool.exception;

public class FlightCapacityExceededException extends RuntimeException{

    public FlightCapacityExceededException(String message) {
        super(message);
    }
}
