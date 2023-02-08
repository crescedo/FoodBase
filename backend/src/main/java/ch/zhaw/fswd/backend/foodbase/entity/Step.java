package ch.zhaw.fswd.backend.foodbase.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import java.util.List;
import jakarta.persistence.Table;
@Entity
@Data
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    
    private String content;
     
    @OneToMany
    private List<Image> images;  
    
    private Integer stepOrder;
}
