package hu.yokudlela.foodAndDrinks.service;

import hu.yokudlela.foodAndDrinks.datamodel.Food;
import hu.yokudlela.foodAndDrinks.store.FoodRepository;

public class AvailableFoodService {
    FoodRepository foodRepository = new FoodRepository();

    public Food getFoodByName(String name){
        return foodRepository.getByName(name);
    }
}
