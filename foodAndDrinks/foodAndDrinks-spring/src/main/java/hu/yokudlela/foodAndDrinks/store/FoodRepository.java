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
     * Összes étel lekérdezése
     * @throws Exception nincs étel felvéve
     * @return asztal
     */
    public List<Food> getAllFood() throws Exception{
        if (FoodRepository.foodList.isEmpty())
            throw new Exception("nincs étel felvéve");

        return FoodRepository.foodList;
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

    /**
     * Új étel létesítése az étteremben
     * @param pFood az új Étel neve
     * @throws java.lang.Exception  ha már ilyen névvel létezik étel
     */
    public void add(Food pFood) throws Exception{
        if(getOptionalByName(pFood.getName()).isEmpty()){
            FoodRepository.foodList.add(pFood);
        }
        else throw new Exception();
    }

    /**
     * Meglévő étel megszüntetése az ételapon
     * @param pName az étel neve
     * @return sikeres művelet esetén true;
     */
    public boolean delete(String pName){
        Optional<Food> tmp = getOptionalByName(pName);
        if(tmp.isEmpty()) return false;
        return FoodRepository.foodList.remove(tmp.get());
    }


}
