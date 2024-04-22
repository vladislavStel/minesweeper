package ru.marcusrite.minesweeper.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.marcusrite.minesweeper.enums.CellStatus;
import ru.marcusrite.minesweeper.exception.error.InvalidParameterException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
// Класс содержит основную логику игры
public class Board {

    private Location location;
    private final int width;
    private final int height;
    private final int mineCount;
    private final Set<Location> visitedLocations;
    private final Random random;
    private String[][] fieldDto;
    private CellStatus[][] minefield;
    private final List<Location> locationsMine;
    private boolean firstTurn;
    private boolean completed;
    private int gameCount;

    public Board(int width, int height, int mineCount) {
        this.width = width;
        this.height = height;
        this.mineCount = mineCount;
        this.completed = false;
        this.firstTurn = true;

        random = new Random();
        locationsMine = new ArrayList<>();
        visitedLocations = new HashSet<>();
        fieldDto = new String[height][width];
        minefield = new CellStatus[height][width];
        gameCount = fieldDto.length * fieldDto[0].length;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                fieldDto[row][col] = CellStatus.NULL.getValue();
                minefield[row][col] = CellStatus.ZERO;
            }
        }
    }

    public void init(int col, int row) {
        if (!fieldDto[row][col].equals(CellStatus.NULL.getValue())) {
            throw new InvalidParameterException("уже открытая ячейка");
        }
        location = new Location(col, row);
        if (firstTurn) {
            placeMines(col, row);
            firstTurn = false;
            turn(location);
        } else {
            turn(location);
        }
        if (gameCount == (fieldDto.length * fieldDto[0].length - mineCount)) {
            for (Location locMine : locationsMine) {
                fieldDto[locMine.getRow()][locMine.getCol()] = CellStatus.MINE.getValue();
            }
            completed = true;
        }
    }

    private void turn(Location location) {
        gameCount++;
        switch (getMinefield(location)) {
            case ZERO:
                fieldDto[location.getRow()][location.getCol()] = CellStatus.ZERO.getValue();
                openAdjacentCells(location);
                break;
            case MINE:
                for (Location locMine : locationsMine) {
                    minefield[locMine.getRow()][locMine.getCol()] = CellStatus.CROSS;
                }
                for (int x = 0; x < height; x++) {
                    for (int y = 0; y < width; y++) {
                        fieldDto[x][y] = minefield[x][y].getValue();
                    }
                }
                completed = true;
                break;
            default:
                fieldDto[location.getRow()][location.getCol()] = getMinefield(location).getValue();
                break;
        }
    }

    // Метод открытия полей вокруг пустой ячейки
    private void openAdjacentCells(Location location) {
        for (Location loc : getLocationsAround(location)) {
            if (!visitedLocations.contains(loc)) {
                visitedLocations.add(loc);
                turn(loc);
            }
        }
    }

    // Метод для формирования координат мин
    private void placeMines(int col, int row) {
        int count = mineCount;
        while (count-- > 0) {
            Location loc = getRandomLocation();
            if (loc.getCol() != col || loc.getRow() != row) {
                if (minefield[loc.getRow()][loc.getCol()].equals(CellStatus.MINE)) {
                    count++;
                } else {
                    locationsMine.add(loc);
                    minefield[loc.getRow()][loc.getCol()] = CellStatus.MINE;
                    putNumbersAroundMine(loc);
                }
            } else {
                count++;
            }
        }
    }

    // Метод получения случайных координат
    private Location getRandomLocation() {
        return new Location(random.nextInt(height), random.nextInt(width));
    }

    // Метод для добавления номеров вокруг мины
    private void putNumbersAroundMine(Location location) {
        for(Location aroundMine : getLocationsAround(location)) {
            if (!CellStatus.MINE.equals(getMinefield(aroundMine))) {
                CellStatus newStatus = getMinefield(aroundMine).getNextNumber();
                minefield[aroundMine.getRow()][aroundMine.getCol()] = newStatus;
            }
        }
    }

    // Метод получения списка всех координат вокруг какой-нибудь точки
    private List<Location> getLocationsAround(Location location) {
        Location around;
        List<Location> locationsAround = new ArrayList<>();
        for (int x = location.getCol() - 1; x <= location.getCol() + 1; x++) {
            for (int y = location.getRow() - 1; y <= location.getRow() + 1; y++) {
                around = new Location(x, y);
                if (inRange(around) && (!around.equals(location))) {
                    locationsAround.add(around);
                }
            }
        }
        return locationsAround;
    }

    // Метод проверки точки, что она в пределах игрового поля
    private boolean inRange(Location location) {
        return location.getCol() >= 0 && location.getCol() < width
                && location.getRow() >= 0 && location.getRow() < height;
    }

    // Метод для получения статуса ячейки по координатам
    private CellStatus getMinefield(Location location) {
        return minefield[location.getRow()][location.getCol()];
    }

}