package hu.yokudlela.foodAndDrinks.utils.logging;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target( {ElementType.METHOD, ElementType.TYPE})
public @interface AspectLogger {
    int[] skipParametersByIndex() default {};
}
