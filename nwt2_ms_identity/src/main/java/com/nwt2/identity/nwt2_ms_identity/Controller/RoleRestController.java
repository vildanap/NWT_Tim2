package com.nwt2.identity.nwt2_ms_identity.Controller;

import com.nwt2.identity.nwt2_ms_identity.Model.Role;
import com.nwt2.identity.nwt2_ms_identity.Model.User;
import com.nwt2.identity.nwt2_ms_identity.Repository.RoleRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RoleRestController {

    private final RoleRepository roleRepository;

    public RoleRestController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseEntity<?> readRoles() {
        List<Role> roles = (List<Role>) this.roleRepository.findAll();
        if (roles.isEmpty()) {
            return new ResponseEntity(new CustomErrorType("No roles found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
    }

    // -------------------Retrieve One User---------------------------------------------
    @RequestMapping(method = RequestMethod.GET, value = "/{roleId}")
    public  ResponseEntity<?> readRole(@PathVariable Long roleId) {
        Optional<Role> role = this.roleRepository.findById(roleId);
        if (!role.isPresent()) {
            return new ResponseEntity(new CustomErrorType("Role with id " + roleId + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Role>>(role, HttpStatus.OK);
    }

    // CREATE

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@Valid @RequestBody Role role, UriComponentsBuilder ucBuilder) {

        if (roleRepository.existsByName(role.getName())) {
            return new ResponseEntity(new CustomErrorType("Unable to create. A role with name " +
                    role.getName() + " already exist."), HttpStatus.CONFLICT);
        }
        roleRepository.save(role);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/roles/{id}").buildAndExpand(role.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // UPDATE

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<?> updateRole(@Valid @PathVariable("id") long id, @RequestBody Role role) {

        Optional<Role> roleExisting = this.roleRepository.findById(id);

        if (!roleExisting.isPresent()) {
            return new ResponseEntity(new CustomErrorType("Unable to update. Role with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        if(roleRepository.existsByName(role.getName())) {
            return new ResponseEntity(new CustomErrorType("Role with the same name already exists."),
                    HttpStatus.CONFLICT);
        }

        roleExisting.get().setName(role.getName());

        this.roleRepository.save(roleExisting.get());
        return new ResponseEntity<Optional<Role>>(roleExisting, HttpStatus.OK);
    }

    // DELETE
    @RequestMapping(method = RequestMethod.DELETE, value = "/{roleId}")
    public ResponseEntity<?> deleteRole(@PathVariable Long roleId) {
        Optional<Role> role = this.roleRepository.findById(roleId);
        if (!role.isPresent()) {
            return new ResponseEntity(new CustomErrorType("Unable to delete. Role with id " + roleId + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        this.roleRepository.deleteById(roleId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
