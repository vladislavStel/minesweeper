package ru.marcusrite.minesweeper.service;

import org.springframework.stereotype.Service;
import ru.marcusrite.minesweeper.dto.GameInfoResponse;
import ru.marcusrite.minesweeper.dto.GameTurnRequest;
import ru.marcusrite.minesweeper.dto.NewGameRequest;
import ru.marcusrite.minesweeper.exception.error.InvalidParameterException;
import ru.marcusrite.minesweeper.exception.error.InvalidParameterMineCountException;
import ru.marcusrite.minesweeper.exception.error.WrongValidationGameIdException;
import ru.marcusrite.minesweeper.mapper.GameInfoMapper;
import ru.marcusrite.minesweeper.model.Game;

import java.util.HashMap;
import java.util.Map;

@Service
public class DefaultGameService implements GameService {

    private final Map<String, Game> games = new HashMap<>();

    // Метод принимает данные о новой игре и создает игру с уникальным id
    @Override
    public GameInfoResponse createGame(NewGameRequest newGameRequest) {
        if (validate(newGameRequest)) {
            throw new InvalidParameterMineCountException("Превышено максимальное значение мин!");
        }
        Game game = new Game(newGameRequest.getWidth(), newGameRequest.getHeight(), newGameRequest.getMinesCount());
        games.put(game.getId().toString(), game);
        return GameInfoMapper.toGameInfoResponse(game);
    }

    // Метод принимает информацию о ходе в игре и проверяет наличие и статус игры
    @Override
    public GameInfoResponse nextTurn(GameTurnRequest gameTurnRequest) {
        Game game = games.get(gameTurnRequest.getGameId());
        if (game != null) {
            if (game.getBoard().isCompleted()) {
                throw new InvalidParameterException("игра завершена");
            }
            game.play(gameTurnRequest.getCol(), gameTurnRequest.getRow());
            return GameInfoMapper.toGameInfoResponse(game);
        }
        else {
            throw new WrongValidationGameIdException("игра с идентификатором " + gameTurnRequest.getGameId()
                    + " не была создана или устарела (неактуальна)");
        }
    }

    // Метод валидации количества мин на игровом поле
    private boolean validate(NewGameRequest newGameRequest) {
        return newGameRequest.getMinesCount() > newGameRequest.getWidth() * newGameRequest.getHeight() - 1;
    }

}