package ch.zhaw.fswd.backend.foodbase.entity;

import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "UserTable")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Cascade(CascadeType.ALL)
    @OneToOne
    private UserInfo userInfo;// basisinformationen aller user

    // private String biographyShort;

    @Cascade(CascadeType.ALL)
    @OneToOne
    private LoginInfo loginInfo; // login Informationen

    @Cascade(CascadeType.ALL)
    @OneToMany(fetch = FetchType.EAGER)
    private List<Recipe> favorites; // eine Liste, wo alle favoriten gespeichert werden
    
    @Cascade(CascadeType.ALL)
    @OneToMany(fetch = FetchType.EAGER)
    private List<Cooking> cookings; // alle Rezepte die gekocht werden

    /*
     * @OneToMany
     * List<User> followers; //alle users, die ein User folgt
     */
}
