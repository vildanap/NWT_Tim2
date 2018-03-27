package com.nwt2.identity.nwt2_ms_identity.Controller;

import com.nwt2.identity.nwt2_ms_identity.Model.User;
import com.nwt2.identity.nwt2_ms_identity.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public @ResponseBody
    Collection<User> readUsers() {

        return this.userRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public @ResponseBody
    Optional<User> readUser(@RequestParam Long userId) {

        return this.userRepository.findById(userId);
    }


}
