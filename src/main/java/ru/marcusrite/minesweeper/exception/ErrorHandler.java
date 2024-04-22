package ru.marcusrite.minesweeper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.marcusrite.minesweeper.exception.error.InvalidParameterException;
import ru.marcusrite.minesweeper.exception.error.InvalidParameterMineCountException;
import ru.marcusrite.minesweeper.exception.error.WrongValidationGameIdException;
import ru.marcusrite.minesweeper.exception.model.ErrorResponse;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({InvalidParameterMineCountException.class, WrongValidationGameIdException.class,
            InvalidParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIncorrectlyMadeRequest(Exception e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleThrowable(Exception e) {
        return new ErrorResponse(e.getMessage());
    }

}