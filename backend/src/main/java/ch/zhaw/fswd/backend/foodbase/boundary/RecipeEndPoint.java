package ch.zhaw.fswd.backend.foodbase.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.fswd.backend.foodbase.entity.IngredientQuantity;
import ch.zhaw.fswd.backend.foodbase.entity.Recipe;
import ch.zhaw.fswd.backend.foodbase.entity.RoleRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class RecipeEndPoint {

    @Autowired
    private RoleRepository recipeRepository;

    @GetMapping(path = "/api/recipes")
    public List<Recipe> getAllRecipes() {

        List<Recipe> recipies = recipeRepository.findAll();

        return recipies;
    }

    @GetMapping(path = "/api/recipes/{id}")
    public @ResponseBody ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {

        Optional<Recipe> recipe = recipeRepository.findById(id.toString());

        if (recipe.isPresent()) {

            return new ResponseEntity<Recipe>(recipe.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/api/recipes/{id}/ingredients")
    public @ResponseBody ResponseEntity<List<IngredientQuantity>> getIngredientsQuantityRecipeById(
            @PathVariable Long id) {

        Optional<Recipe> recipe = recipeRepository.findById(id.toString());

        if (recipe.isPresent()) {

            List<IngredientQuantity> ingredients = recipe.get().getIngredients();

            if (ingredients != null) {
                return new ResponseEntity<List<IngredientQuantity>>(ingredients, HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  

    @PostMapping(path = "/api/recipes")
    public @ResponseBody ResponseEntity<Recipe> saveRecipe(@RequestBody Recipe newRecipe) {

        Optional<Recipe> recipe = recipeRepository.findById(newRecipe.getId().toString());
        if (!recipe.isPresent()) {

            recipeRepository.save(newRecipe);

            return new ResponseEntity<Recipe>(newRecipe, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
