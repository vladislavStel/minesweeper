package ru.marcusrite.minesweeper.exception.error;

public class WrongValidationGameIdException extends RuntimeException {

    public WrongValidationGameIdException(String message) {
        super(message);
    }
}