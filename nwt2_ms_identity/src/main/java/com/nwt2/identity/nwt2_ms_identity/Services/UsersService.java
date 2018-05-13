package com.nwt2.identity.nwt2_ms_identity.Services;

import com.nwt2.identity.nwt2_ms_identity.Model.User;
import com.nwt2.identity.nwt2_ms_identity.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@Component
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    public Collection< User > findAll() {
        return userRepository.findAll();
    }

    public void deleteById(Long id)
    {
        userRepository.deleteById(id);
    }


    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public void saveUser(@Valid User user) {
        userRepository.save(user);
    }
}
