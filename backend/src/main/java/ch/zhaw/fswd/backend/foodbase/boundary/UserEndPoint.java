package ch.zhaw.fswd.backend.foodbase.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ch.zhaw.fswd.backend.foodbase.entity.User;
import ch.zhaw.fswd.backend.foodbase.entity.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserEndPoint {

    @Autowired
    private UserRepository userRepository;

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
}
