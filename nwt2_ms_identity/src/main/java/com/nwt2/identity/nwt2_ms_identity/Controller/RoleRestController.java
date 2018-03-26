package com.nwt2.identity.nwt2_ms_identity.Controller;

import com.nwt2.identity.nwt2_ms_identity.Model.Role;
import com.nwt2.identity.nwt2_ms_identity.Model.User;
import com.nwt2.identity.nwt2_ms_identity.Repository.RoleRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RoleRestController {

    private final RoleRepository roleRepository;

    public RoleRestController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    Collection<Role> readRoles() {

        return this.roleRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{roleId}")
    Optional<Role> readRole(@PathVariable Long roleId) {

        return this.roleRepository.findById(roleId);
    }
}
