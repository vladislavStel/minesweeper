package ru.marcusrite.minesweeper.exception.error;

public class InvalidParameterMineCountException extends RuntimeException {

    public InvalidParameterMineCountException(final String message) {
        super(message);
    }
}