package hu.yokudlela.foodAndDrinks.store.service;

import hu.yokudlela.foodAndDrinks.datamodel.Food;
import hu.yokudlela.foodAndDrinks.store.FoodRepository;

import java.util.List;

public class AvailableFoodService {
    FoodRepository foodRepository = new FoodRepository();

    public Food getFoodByName(String name){
        return foodRepository.getByName(name);
    }
}
