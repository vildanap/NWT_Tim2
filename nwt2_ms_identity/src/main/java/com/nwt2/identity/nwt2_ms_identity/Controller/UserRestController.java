package com.nwt2.identity.nwt2_ms_identity.Controller;

import com.nwt2.identity.nwt2_ms_identity.Model.User;
import com.nwt2.identity.nwt2_ms_identity.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserRepository userRepository;

    @Autowired
    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    Collection<User> readUsers() {

        return this.userRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    Optional<User> readUser(@PathVariable Long userId) {

        return this.userRepository.findById(userId);
    }

}
