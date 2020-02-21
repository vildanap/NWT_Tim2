package com.nwt2.identity.nwt2_ms_identity.Controller;

import com.nwt2.identity.nwt2_ms_identity.Model.Role;
import com.nwt2.identity.nwt2_ms_identity.Model.User;
import com.nwt2.identity.nwt2_ms_identity.Services.UserDetailsService;
import com.nwt2.identity.nwt2_ms_identity.Services.UserEventHandler;
import com.nwt2.identity.nwt2_ms_identity.Services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RefreshScope
@RequestMapping("/users")
@EnableJpaRepositories("com.nwt2.identity")
public class UserRestController {

  @Autowired
  private UserEventHandler eh;

  @Autowired
  private UserDetailsService usersService;


    // -------------------Retrieve All Users---------------------------------------------
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public  ResponseEntity<?> readUsers() {
        List<User> users = (List<User>) this.usersService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity(new CustomErrorType("No users found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    // -------------------Retrieve One User---------------------------------------------
    @RequestMapping(method = RequestMethod.GET, value = "/find/{userId}")
    public ResponseEntity<?> readUser(@PathVariable Long userId) {
        Optional<User> user = this.usersService.findById(userId);
        if (!user.isPresent()) {
            return new ResponseEntity(new CustomErrorType("User with id " + userId + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);
    }

    // -------------------Retrieve One User By Username---------------------------------------------
    @RequestMapping(value = "/findUsername/{userName}", method = RequestMethod.GET)
    public ResponseEntity<User> findByUserName(@PathVariable String userName) {
        User user = this.usersService.findByUsername(userName);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }



    // CREATE

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, UriComponentsBuilder ucBuilder) {

        if (usersService.existsByUsername(user.getUsername())) {
            return new ResponseEntity(new CustomErrorType("Unable to create. A User with username " +
                    user.getUsername() + " already exist."), HttpStatus.CONFLICT);
        }

        // encode the password
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        usersService.saveUser(user);
        eh.handleAfterCreated(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // UPDATE

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<?> updateUser(@Valid @PathVariable("id") long id, @RequestBody User user) {

        Optional<User> userAccount = this.usersService.findById(id);

        if (!userAccount.isPresent()) {
            return new ResponseEntity(new CustomErrorType("Unable to update. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        if(usersService.existsByUsername(user.getUsername())) {
            return new ResponseEntity(new CustomErrorType("User with the same username already exists."),
                    HttpStatus.CONFLICT);
        }

     userAccount.get().setUsername(user.getUsername());

        this.usersService.saveUser(userAccount.get());
        eh.handleUserSave(userAccount.get());
        return new ResponseEntity<Optional<User>>(userAccount, HttpStatus.OK);
    }

    // DELETE
    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        Optional<User> user = this.usersService.findById(userId);
        if (!user.isPresent()) {
            return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + userId + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        this.usersService.deleteById(userId);
        eh.handleAfterDeleted(user.get());
        return new ResponseEntity(HttpStatus.OK);
    }


}
