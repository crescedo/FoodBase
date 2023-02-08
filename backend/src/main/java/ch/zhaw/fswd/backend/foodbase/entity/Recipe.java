package ch.zhaw.fswd.backend.foodbase.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    @ManyToOne
    private User createdBy;

    @OneToMany
    private List<Step> cookingSteps;

    @OneToMany
    private List<IngredientQuantity> ingredients;

    private List<String> keyWords;

    @OneToOne
    private Image thumbnailUrl; // Link zu einem Bild der als thumbnail verwendet wird

    @ManyToOne
    private Category category;

    private Integer difficulty; // difficulty as 3/5 chef hats

    private Double cookingTime; //Kochzeit des Rezepts

    private Integer servings; //für wieviele ist das Rezept gedacht
}
