package ch.zhaw.fswd.backend.foodbase.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
