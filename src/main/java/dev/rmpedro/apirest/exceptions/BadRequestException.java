package dev.rmpedro.apirest.exceptions;

public class BadRequestException extends RuntimeException{


    public BadRequestException(String message) {
        super(message);
    }
}
