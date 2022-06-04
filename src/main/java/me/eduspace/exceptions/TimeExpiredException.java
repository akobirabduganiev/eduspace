package me.eduspace.exceptions;

public class TimeExpiredException extends RuntimeException{
    public TimeExpiredException(String message) {
        super(message);
    }
}
