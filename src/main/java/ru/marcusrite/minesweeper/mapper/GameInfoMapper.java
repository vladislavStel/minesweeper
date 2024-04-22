package ru.marcusrite.minesweeper.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.marcusrite.minesweeper.dto.GameInfoResponse;
import ru.marcusrite.minesweeper.model.Game;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GameInfoMapper {

    public static GameInfoResponse toGameInfoResponse(Game game) {
        return GameInfoResponse.builder()
                .gameId(game.getId().toString())
                .width(game.getWidth())
                .height(game.getHeight())
                .minesCount(game.getMinesCount())
                .completed(game.getBoard().isCompleted())
                .field(game.getBoard().getFieldDto())
                .build();
    }

}