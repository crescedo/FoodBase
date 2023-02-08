package ch.zhaw.fswd.backend.foodbase.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import java.time.LocalDateTime;
@Entity
@Data
public class Cooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    private Recipe recipe; //was soll gekocht werden?
    
    private LocalDateTime time; //Wann soll gekocht werden?

    private Integer guests;//für wieviele Leute soll gekocht werden?

}
