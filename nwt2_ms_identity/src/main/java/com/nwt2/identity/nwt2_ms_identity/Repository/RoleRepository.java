package com.nwt2.identity.nwt2_ms_identity.Repository;

import com.nwt2.identity.nwt2_ms_identity.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

}