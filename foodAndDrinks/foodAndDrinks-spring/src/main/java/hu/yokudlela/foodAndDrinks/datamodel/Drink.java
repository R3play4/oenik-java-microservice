package hu.yokudlela.foodAndDrinks.datamodel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode()
@NoArgsConstructor
@Schema(description = "Ital")
public class Drink {
    @Schema(description = "Ital neve")
    private String name;
    @Schema(description = "Ital leírása")
    private String description;
    @Schema(description = "Ital ára")
    private int price;
    @Schema(description = "Ital mennyisége")
    private float amount;

    @Builder
    public Drink(String name, String description, int price, float amount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }
}
