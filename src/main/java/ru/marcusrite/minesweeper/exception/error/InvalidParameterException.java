package ru.marcusrite.minesweeper.exception.error;

public class InvalidParameterException extends RuntimeException {

    public InvalidParameterException(final String message) {
        super(message);
    }
}