package hu.yokudlela.foodAndDrinks.datamodel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode()
@NoArgsConstructor
@Schema(description = "Etel")
public class Food {
    @Schema(description = "Étel neve")
    private String name;
    @Schema(description = "Étel leírása")
    private String description;
    @Schema(description = "Étel ára")
    private int price;

    @Builder
    public Food(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
