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

import ch.zhaw.fswd.backend.foodbase.controller.RecipeController;
import ch.zhaw.fswd.backend.foodbase.entity.IngredientQuantity;
import ch.zhaw.fswd.backend.foodbase.entity.Recipe;
import ch.zhaw.fswd.backend.foodbase.entity.RecipeRepository;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@RestController
@CrossOrigin
public class RecipeEndPoint {

    @Autowired
    private RecipeController recipeController;

    @RequestMapping(path = "/api/recipes", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public List<Recipe> getAllRecipes() {

        List<Recipe> recipies = recipeController.getAllRecipes();

        return recipies;
    }

    @RequestMapping(path = "/api/recipes/{id}", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public @ResponseBody ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {

        Optional<Recipe> recipe = recipeController.findRecipeById(id);

        if (recipe.isPresent()) {

            return new ResponseEntity<Recipe>(recipe.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/api/recipes/{id}/ingredients")
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public @ResponseBody ResponseEntity<List<IngredientQuantity>> getIngredientsQuantityRecipeById(
            @PathVariable Long id) {

        Optional<Recipe> recipe = recipeController.findRecipeById(id);

        if (recipe.isPresent()) {

            List<IngredientQuantity> ingredients = recipe.get().getIngredients();

            if (ingredients != null) {
                return new ResponseEntity<List<IngredientQuantity>>(ingredients, HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  

    @PostMapping(path = "/api/recipes")
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public @ResponseBody ResponseEntity<Recipe> saveRecipe(@RequestBody Recipe newRecipe) {

        Optional<Recipe> recipe = recipeController.findRecipeById(newRecipe.getId());
        if (!recipe.isPresent()) {

            recipeController.addRecipe(newRecipe);

            return new ResponseEntity<Recipe>(newRecipe, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
