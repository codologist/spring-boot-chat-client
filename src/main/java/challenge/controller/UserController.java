package challenge.controller;

import challenge.models.User;
import challenge.repositories.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRespository userRespository;

    @PostMapping(value = "/createUser")
    public User addUser(@Valid @RequestBody User user) {
        Example<User> example = Example.of(user);
        if(userRespository.exists(example)) {

            throw new IllegalArgumentException("User already exists " + user.getUsername());
        }
        return userRespository.save(user);
    }

    @GetMapping(value = "/getAllUsers")
    public List<User> getAllUsers() {
        return userRespository.findAll();
    }

}
