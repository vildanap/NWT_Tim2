package com.nwt2.identity.nwt2_ms_identity.Repository;

import com.nwt2.identity.nwt2_ms_identity.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RepositoryRestResource(collectionResourceRel = "user", path = "users")
public interface UserRepository extends JpaRepository<User, Long> {

   Optional<User> findById(@Param("id") Long id);
   // Optional<User> findByUsername(@Param("username") String username);
    //User findByName(String name);
    boolean existsByUsername(String username);
    boolean existsById(Long id);

}

