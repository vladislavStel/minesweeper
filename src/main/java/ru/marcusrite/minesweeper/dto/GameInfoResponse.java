package ru.marcusrite.minesweeper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonPropertyOrder({ "gameId", "width" , "height", "minesCount", "field", "completed"})
public class GameInfoResponse {

    @JsonProperty("game_id")
    String gameId;

    int width;
    int height;

    @JsonProperty("mines_count")
    int minesCount;

    boolean completed;
    String[][] field;

}