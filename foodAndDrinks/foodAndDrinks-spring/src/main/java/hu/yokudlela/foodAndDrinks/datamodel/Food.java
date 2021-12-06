package hu.yokudlela.foodAndDrinks.datamodel;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class Food {
    private String name;
    private String description;
    private int price;

    @Builder
    public Food(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
