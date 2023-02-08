package ch.zhaw.fswd.backend.foodbase.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "UserTable")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private UserInfo userInfo;// basisinformationen aller user

    // private String biographyShort;

    @OneToOne
    private LoginInfo loginInfo; // login Informationen

    @OneToMany
    private List<Recipe> favorites; // eine Liste, wo alle favoriten gespeichert werden

    @OneToMany
    private List<Cooking> cookings; // alle Rezepte die gekocht werden
    
    /*
     * @OneToMany
     * List<User> followers; //alle users, die ein User folgt
     */
}
