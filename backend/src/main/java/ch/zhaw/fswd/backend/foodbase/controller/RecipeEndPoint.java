package ch.zhaw.fswd.backend.foodbase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.fswd.backend.foodbase.entity.RecipeRepository;

@RestController
@CrossOrigin
public class RecipeEndPoint {
    @Autowired
    private RecipeRepository recipeRepository;
}
