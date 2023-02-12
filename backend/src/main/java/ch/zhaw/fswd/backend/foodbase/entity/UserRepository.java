package ch.zhaw.fswd.backend.foodbase.entity;

import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u.id FROM User AS u JOIN  LoginInfo  AS l ON u.loginInfo.id=l.id WHERE l.loginName=?1")
    Optional<Long> findUserByLoginName( String loginName);

    @Query("SELECT f.id from User u JOIN u.favorites AS f WHERE u.id=?1 ")
    List<Long> getFavoritesById(Long id);

    @Query("SELECT u.favorites FROM User as u WHERE u.id=?1")
    List<Recipe> getFavoritesByUserId(Long id);
}
