package ru.marcusrite.minesweeper.service;

import ru.marcusrite.minesweeper.dto.GameInfoResponse;
import ru.marcusrite.minesweeper.dto.GameTurnRequest;
import ru.marcusrite.minesweeper.dto.NewGameRequest;

public interface GameService {

    GameInfoResponse createGame(NewGameRequest newGameRequest);

    GameInfoResponse nextTurn(GameTurnRequest gameTurnRequest);

}