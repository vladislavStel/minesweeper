package ru.marcusrite.minesweeper.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
// На основе класса формируется DTO на фронт.
public class Game {

    private final Integer width;
    private final Integer height;
    private final Integer minesCount;

    private final UUID id;
    private Board board;

    public Game(int width, int height, int minesCount) {
        this.width = width;
        this.height = height;
        this.minesCount = minesCount;

        this.id = UUID.randomUUID();
        board = new Board(width, height, minesCount);
    }

    // Метод для запуска игры
    public void play(int col, int row) {
        board.init(col, row);
    }

}