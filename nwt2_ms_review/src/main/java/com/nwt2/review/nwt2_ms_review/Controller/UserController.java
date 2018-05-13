package com.nwt2.review.nwt2_ms_review.Controller;

import com.nwt2.review.nwt2_ms_review.Model.User;
import com.nwt2.review.nwt2_ms_review.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ohrinator on 4/3/18.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<?> store(@RequestBody User user) {
        this.userRepository.save(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
}
