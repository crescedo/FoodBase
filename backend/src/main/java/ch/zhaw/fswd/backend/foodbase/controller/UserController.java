package ch.zhaw.fswd.backend.foodbase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import ch.zhaw.fswd.backend.foodbase.entity.LoginInfo;
import ch.zhaw.fswd.backend.foodbase.entity.Role;
import ch.zhaw.fswd.backend.foodbase.entity.User;
import ch.zhaw.fswd.backend.foodbase.entity.UserRepository;

@Component
public class UserController {
    @Autowired
    private UserRepository userRepository;

    public void persistNewUser(LoginInfo login){
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

    
}
