package com.nwt2.identity.nwt2_ms_identity.Controller;

import com.nwt2.identity.nwt2_ms_identity.Model.User;
import com.nwt2.identity.nwt2_ms_identity.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
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

    // -------------------Retrieve All Users---------------------------------------------
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public  ResponseEntity<?> readUsers() {
        List<User> users = (List<User>) this.userRepository.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity(new CustomErrorType("No users found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    // -------------------Retrieve One User---------------------------------------------
    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public  ResponseEntity<?> readUser(@PathVariable Long userId) {
        Optional<User> user = this.userRepository.findById(userId);
        if (!user.isPresent()) {
            return new ResponseEntity(new CustomErrorType("User with id " + userId + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);
    }



    // CREATE

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<?> createUser( @Valid @RequestBody User user, UriComponentsBuilder ucBuilder) {

        if (userRepository.existsByUsername(user.getUsername())) {
            return new ResponseEntity(new CustomErrorType("Unable to create. A User with username " +
                    user.getUsername() + " already exist."), HttpStatus.CONFLICT);
        }
        userRepository.save(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // UPDATE

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<?> updateUser(@Valid @PathVariable("id") long id, @RequestBody User user) {

        Optional<User> userAccount = this.userRepository.findById(id);

        if (!userAccount.isPresent()) {
            return new ResponseEntity(new CustomErrorType("Unable to update. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        if(userRepository.existsByUsername(user.getUsername())) {
            return new ResponseEntity(new CustomErrorType("User with the same username already exists."),
                    HttpStatus.CONFLICT);
        }

     userAccount.get().setUsername(user.getUsername());

        this.userRepository.save(userAccount.get());
        return new ResponseEntity<Optional<User>>(userAccount, HttpStatus.OK);
    }

    // DELETE
    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        Optional<User> user = this.userRepository.findById(userId);
        if (!user.isPresent()) {
            return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + userId + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        this.userRepository.deleteById(userId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
