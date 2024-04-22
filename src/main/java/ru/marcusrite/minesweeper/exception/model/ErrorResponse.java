package ru.marcusrite.minesweeper.exception.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

    private String error;

    public ErrorResponse(final String error) {
        this.error = error;
    }

}