package ch.zhaw.fswd.backend.foodbase.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;
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
