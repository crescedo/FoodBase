package ch.zhaw.fswd.backend.foodbase.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import ch.zhaw.fswd.backend.foodbase.entity.Category;
import ch.zhaw.fswd.backend.foodbase.entity.CategoryRepository;


import java.util.List;

@RestController
@CrossOrigin
public class CategoryEndPoint {
        
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(path = "/api/categories",method=RequestMethod.GET)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public List<Category> getAllCategories() {

        List<Category> categories = categoryRepository.findAll();

        return categories;
    } 
}
