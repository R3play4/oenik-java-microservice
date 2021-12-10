package hu.yokudlela.foodAndDrinks.rest;

import hu.yokudlela.foodAndDrinks.datamodel.Food;
import hu.yokudlela.foodAndDrinks.store.FoodRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping(path = "/food")
public class FoodController {

    @Autowired
    private FoodRepository foodService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sikeres lekérdezés",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Food.class))) }),
    })
    @Operation(summary = "Étel lekérdezése név alapján")
    @GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public Food getFood(
            @Parameter(description = "Étel neve", required = true) @RequestParam(name = "food", required = true) String food){
        return foodService.getByName(food);
    }


}
