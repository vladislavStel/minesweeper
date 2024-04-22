package ru.marcusrite.minesweeper.enums;

import lombok.Getter;

@Getter
public enum CellStatus {

    NULL(" "),
    ZERO("0"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    MINE("M"),
    CROSS("X");

    private final String value;

    CellStatus(String value) {
        this.value = value;
    }

    public CellStatus getNextNumber() {
        return CellStatus.values() [this.ordinal() + 1];
    }

}