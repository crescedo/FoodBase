package ch.zhaw.fswd.backend.foodbase.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.zhaw.fswd.backend.foodbase.entity.Recipe;
import ch.zhaw.fswd.backend.foodbase.entity.RecipeRepository;

@Component
public class RecipeController{

    @Autowired
    private RecipeRepository recipeRepository;

    public List<Recipe> listAllRecipes(Long creatorId){
        return recipeRepository.findAllRecipesByCreatorId(creatorId);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<Recipe> findRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    public void addRecipe(Recipe newRecipe) {
        recipeRepository.save(newRecipe);
    }

}