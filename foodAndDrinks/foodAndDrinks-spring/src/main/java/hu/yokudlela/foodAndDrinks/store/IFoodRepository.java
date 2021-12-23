package hu.yokudlela.foodAndDrinks.store;

import hu.yokudlela.foodAndDrinks.datamodel.Food;

import java.util.List;

public interface IFoodRepository {

    /**
     * Étel lekérdezés név alapján
     * @param pName aztal egyedi neve
     * @throws java.lang.NullPointerException Nincs ilyen nevű étel
     * @return étel
     */
    public Food findByName(String pName);

    /**
     * Új étel létesítése
     * @param pTable az új étel leírása
     * @throws java.lang.Exception  ha már ilyen névvel létezik étel
     */
    public Food save(Food pTable);

    /**
     * Meglévő étel megszüntetése az étteremben
     * @param pTable az étel leírása
     * @return sikeres művelet esetén true;
     */
    public void delete(Food pTable);

}
