package com.nwt2.identity.nwt2_ms_identity.Repository;

import com.nwt2.identity.nwt2_ms_identity.Model.Role;
import com.nwt2.identity.nwt2_ms_identity.Model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
}

