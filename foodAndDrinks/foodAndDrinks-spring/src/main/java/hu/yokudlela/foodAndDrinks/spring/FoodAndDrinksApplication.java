package hu.yokudlela.foodAndDrinks.spring;

import hu.yokudlela.foodAndDrinks.datamodel.Food;

public class FoodAndDrinksApplication {

    public static void main(String[] args){
        Food food = Food.builder()
                .name("test")
                .description("valami étel")
                .price(1500);

        System.out.println(food.getName());
    }
}
