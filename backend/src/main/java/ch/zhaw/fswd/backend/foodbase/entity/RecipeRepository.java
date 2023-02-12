package ch.zhaw.fswd.backend.foodbase.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query(value="SELECT r FROM Recipe as r WHERE r.creator.id = ?1" )
    List<Recipe> findAllRecipesByCreatorId( Long creatorId);
    

    @Query("SELECT r FROM Recipe as r WHERE LOWER(r.title) LIKE %:title%")
    List<Recipe> findAllRecipesByRecipeTitle(@Param("title") String title);

}
