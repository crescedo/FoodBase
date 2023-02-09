package ch.zhaw.fswd.backend.foodbase.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.fswd.backend.foodbase.entity.Ingredient;
import ch.zhaw.fswd.backend.foodbase.entity.IngredientRepository;

import java.util.List;

@RestController
@CrossOrigin
public class IngredientEndPoint {
        
    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping(path = "/api/ingredients")
    public List<Ingredient> getAllIngredients() {

        List<Ingredient> ingredients = ingredientRepository.findAll();

        return ingredients;
    } 
}
