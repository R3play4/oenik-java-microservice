package hu.yokudlela.foodAndDrinks.store;

import hu.yokudlela.foodAndDrinks.datamodel.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import lombok.Builder;

import javax.annotation.PostConstruct;

@Service
public class FoodRepository {
    private static final List<Food> foodList = new ArrayList<>();

    @PostConstruct
    public void init(){
        foodList.add(Food.builder()
                .name("teszt")
                .description("csak egy kaja")
                .price(2440)
                .build());
    }
    /**
     * Étel lekérdezés név alapján
     * @param name étel egyedi neve
     * @throws java.lang.NullPointerException Nincs ilyen nevű étel
     * @return asztal
     */
    public Food getByName(String name) throws NullPointerException{
        Optional<Food> tmp = getOptionalByName(name);
        if(tmp.isEmpty()) throw new NullPointerException();
        return tmp.get();
    }

    /**
     * Étel lekérdezés név alapján
     * @param name étel egyedi neve
     * @return étel
     */
    private Optional<Food> getOptionalByName(String name) {
        Optional<Food> result = foodList.stream()
                .filter(element -> element.getName().equals(name))
                .findFirst();
        return result;
    }

}
