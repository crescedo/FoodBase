package ch.zhaw.fswd.backend.foodbase.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class IngredientQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* @OneToOne
    private Recipe recipe;
 */
    private Double quantity;

    @OneToOne
    private Ingredient ingredient;

    @OneToOne
    private Measure measure;

}
