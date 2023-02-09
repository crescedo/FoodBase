package ch.zhaw.fswd.backend.foodbase.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import java.time.Duration;
@Entity
@Data
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private LocalDateTime createdAt;

    @ManyToOne
    private User createdBy;

    @Cascade(CascadeType.ALL)
    @OneToMany
    private List<Step> cookingSteps;

    @Cascade(CascadeType.ALL)
    @OneToMany
    private List<IngredientQuantity> ingredients;

    private List<String> keyWords;
    
    @Cascade(CascadeType.ALL)
    @OneToOne
    private Image thumbnailUrl; // Link zu einem Bild der als thumbnail verwendet wird

    @ManyToOne
    private Category category;

    private Integer difficulty; // difficulty as 3/5 chef hats

    @JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME, 
        include = As.PROPERTY, 
        property = "type")
    private Duration cookingTime; //Kochzeit des Rezepts

    private Integer servings; //für wieviele ist das Rezept gedacht
}
