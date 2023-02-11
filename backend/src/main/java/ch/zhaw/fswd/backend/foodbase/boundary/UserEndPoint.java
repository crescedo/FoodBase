package ch.zhaw.fswd.backend.foodbase.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.fswd.backend.foodbase.controller.UserController;
import ch.zhaw.fswd.backend.foodbase.entity.LoginInfo;
import ch.zhaw.fswd.backend.foodbase.entity.User;
import ch.zhaw.fswd.backend.foodbase.entity.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserEndPoint {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserController userController;

    @GetMapping(path = "/api/users")
    public @ResponseBody ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);

    }

    @GetMapping(path = "/api/users/{id}")
    public @ResponseBody ResponseEntity<User> getUserById(@PathVariable Long id) {

        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {

            return new ResponseEntity<User>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/api/me", method = RequestMethod.GET, produces = "application/json")
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public String me(Principal principal) {

        return "{\"user\": \"" + principal.getName() + "\"} ";
    }

    @RequestMapping(path = "/auth/users", method = RequestMethod.POST)
    public void addNewUser(@RequestBody LoginInfo newUserLoginInfo) {

        userController.persistNewUser(newUserLoginInfo);
    }

}
