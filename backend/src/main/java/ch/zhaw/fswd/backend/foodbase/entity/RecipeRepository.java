package ch.zhaw.fswd.backend.foodbase.entity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RecipeRepository extends JpaRepository<Recipe,Long>{
    
}
