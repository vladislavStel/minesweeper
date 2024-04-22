package ru.marcusrite.minesweeper.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.marcusrite.minesweeper.dto.GameInfoResponse;
import ru.marcusrite.minesweeper.dto.GameTurnRequest;
import ru.marcusrite.minesweeper.dto.NewGameRequest;
import ru.marcusrite.minesweeper.service.GameService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MinesGameController {

    private final GameService gameService;

    // Метод получает настройки игры и возвращает исходное состояние игрового поля с ID игры
    @PostMapping("/new")
    public GameInfoResponse startNewGame(@Valid @RequestBody NewGameRequest newGameRequest) {
        return gameService.createGame(newGameRequest);
    }

    // Метод получает координаты хода и отправляет изменившееся состояние игрового поля по ID игры
    @PostMapping("/turn")
    public GameInfoResponse makeMove(@Valid @RequestBody GameTurnRequest gameTurnRequest) {
        return gameService.nextTurn(gameTurnRequest);
    }

}