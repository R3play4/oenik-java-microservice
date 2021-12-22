package hu.yokudlela.foodAndDrinks.datamodel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode()
@NoArgsConstructor
@Schema(description = "Etel")
public class Food {
    @Schema(description = "Étel neve")
    @NotBlank(message = "error.food.name.notset")
    @NotNull(message = "error.food.name.notset")
    @Size(max=20, message = "error.food.name.long")
    private String name;

    @Schema(description = "Étel leírása")
    @Size(max=100, message = "error.food.name.long")
    private String description;

    @Schema(description = "Étel ára")
    @Min(value = 1, message = "error.food.price.positive")
    private int price;

    @Builder
    public Food(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
