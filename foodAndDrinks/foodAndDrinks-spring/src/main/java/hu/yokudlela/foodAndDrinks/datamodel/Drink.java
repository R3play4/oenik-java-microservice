package hu.yokudlela.foodAndDrinks.datamodel;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class Drink {
    private String name;
    private String description;
    private int price;
    private float amount;

    @Builder
    public Drink(String name, String description, int price, float amount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }
}
