package hu.yokudlela.foodAndDrinks.rest;

import hu.yokudlela.foodAndDrinks.datamodel.Food;
import hu.yokudlela.foodAndDrinks.service.BusinessException;
import hu.yokudlela.foodAndDrinks.store.FoodRepository;
import hu.yokudlela.foodAndDrinks.store.IFoodRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;

@RestController()
@RequestMapping(path = "/admin")

public class AdminController {



    @Autowired
    IFoodRepository foodService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A keresett étel",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Food.class)) }),
            @ApiResponse(responseCode = "500", description = "Sikertelen lekérdezés",
                    content = { @Content(mediaType = "application/json") })
    })
    @Operation(summary = "Asztal lekérdezés név alapján")
    @GetMapping(path = "/getbyname/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Food getByName(
            @Parameter(description="Étel neve", required = true, example = "hús")
            @PathVariable(name = "name", required = true) String pId) throws Exception {
        return foodService.findByName(pId);
    }



    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sikeres felvitel",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Food.class)) }),
            @ApiResponse(responseCode = "403", description = "Nincs megfelelő jogosultságod",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "401", description = "Lejárt token",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "302", description = "Nincs bejelentkezve, átirányítás a login oldalra",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Étel már létezik",
                    content = { @Content(mediaType = "application/json") })
    })
    @Operation(
            security = {
            @SecurityRequirement(name = "apikey",scopes = {"food"}),
            @SecurityRequirement(name = "openid",scopes = {"food"}),
            @SecurityRequirement(name = "oauth2",scopes = {"food"}),
            },
            summary = "Új étel felvitele")
    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed("user")
    public Food add(
            Principal principal,
            @Valid
            @Parameter(description = "Az új étel",required = true) @RequestBody(required = true) Food pData) throws Exception {
        KeycloakPrincipal kPrincipal = (KeycloakPrincipal) principal;
        AccessToken token = kPrincipal.getKeycloakSecurityContext().getToken();
        AccessToken.Access customClaims = token.getResourceAccess("account");
        System.out.println("ROLES:"+customClaims.getRoles());
        foodService.save(pData);
        return pData;
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sikeres művelet",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "403", description = "Nincs megfelelő jogosultságod",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "401", description = "Lejárt token",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "302", description = "Nincs bejelentkezve, átirányítás a login oldalra",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Étel már létezik",
                    content = { @Content(mediaType = "application/json") })
    })
    @Operation(summary = "Étel törlése")
    @DeleteMapping(path = "/delete/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(
            @Parameter(description = "Étel neve", required = true, example = "hús")
            @NotEmpty(message = "error.food.name.notset")
            @NotBlank(message = "error.food.name.notset")
            @PathVariable(name = "name", required = true) String pId) {
        Food tmp = foodService.findByName(pId);
        if(tmp!=null){
            foodService.delete(tmp);
        }
        else{
            throw new BusinessException("food.notexist");
        }
    }
}
