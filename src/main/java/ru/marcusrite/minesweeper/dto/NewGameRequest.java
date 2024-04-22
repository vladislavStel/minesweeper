package ru.marcusrite.minesweeper.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewGameRequest {

    @NotNull(message = "Поле не может быть пустым")
    @Min(value = 2, message = "Ширина поля должна быть не менее 2")
    @Max(value = 30, message = "Ширина поля должна быть не более 30")
    Integer width;

    @NotNull(message = "Поле не может быть пустым")
    @Min(value = 2, message = "Высота поля должна быть не менее 2")
    @Max(value = 30, message = "Высота поля должна быть не более 30")
    Integer height;

    @NotNull(message = "Поле не может быть пустым")
    @JsonProperty("mines_count")
    Integer minesCount;

}