package ch.zhaw.fswd.backend.foodbase.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.fswd.backend.foodbase.entity.Category;
import ch.zhaw.fswd.backend.foodbase.entity.CategoryRepository;


import java.util.List;

@RestController
@CrossOrigin
public class CategoryEndPoint {
        
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(path = "/api/categories")
    public List<Category> getAllCategories() {

        List<Category> categories = categoryRepository.findAll();

        return categories;
    } 
}
