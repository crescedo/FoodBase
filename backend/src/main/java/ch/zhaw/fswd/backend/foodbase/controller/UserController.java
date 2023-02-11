package ch.zhaw.fswd.backend.foodbase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import ch.zhaw.fswd.backend.foodbase.entity.LoginInfo;
import ch.zhaw.fswd.backend.foodbase.entity.Role;
import ch.zhaw.fswd.backend.foodbase.entity.User;
import ch.zhaw.fswd.backend.foodbase.entity.UserRepository;
import ch.zhaw.fswd.backend.foodbase.entity.Recipe;
import java.util.List;

import java.util.Optional;

@Component
public class UserController {
    @Autowired
    private UserRepository userRepository;

    public void persistNewUser(LoginInfo login) {
        User newUser = new User();
        LoginInfo newUserLoginInfo = new LoginInfo();
        newUserLoginInfo.setPasswordHash(new BCryptPasswordEncoder().encode(login.getPasswordHash()));
        Role r = new Role();
        r.setRoleName("ROLE_USER");
        newUserLoginInfo.getRoles().add(r);
        newUserLoginInfo.setRoles(login.getRoles());
        newUser.setLoginInfo(newUserLoginInfo);
        userRepository.save(newUser);
    }

    public void updateFavorites(Recipe recipe, String owner) {
        Optional<Long> userId = userRepository.findUserByLoginName(owner);
        if (userId.isPresent()) {

            Optional<User> user = userRepository.findById(userId.get());
            if (user.isPresent()) {
                
                List<Long> favorites = userRepository.getFavoritesById(user.get().getId());
               
                if (!favorites.contains(recipe.getId())) {
                    user.get().getFavorites().add(recipe);
                    userRepository.save(user.get());
                }
            }
        }
    }

}
