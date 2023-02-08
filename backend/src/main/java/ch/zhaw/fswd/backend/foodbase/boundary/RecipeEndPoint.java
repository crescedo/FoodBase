package ch.zhaw.fswd.backend.foodbase.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.fswd.backend.foodbase.entity.Recipe;
import ch.zhaw.fswd.backend.foodbase.entity.RecipeRepository;

import java.util.List;

@RestController
@CrossOrigin
public class RecipeEndPoint {
        
    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping(path = "/api/recipes")
    public List<Recipe> getAllRecipes() {

        List<Recipe> recipies = recipeRepository.findAll();

        return recipies;
    } 
}
