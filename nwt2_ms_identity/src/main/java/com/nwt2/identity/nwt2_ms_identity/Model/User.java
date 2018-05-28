package com.nwt2.identity.nwt2_ms_identity.Model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "identity_generator")
    @SequenceGenerator(name="identity_generator", sequenceName = "identity_seq", allocationSize=1)
    private Long id;

    @NotEmpty(message="Firstname cannot be empty")
    private String firstName;
    @NotEmpty(message="Lastname cannot be empty")
    private String lastName;

    @NotEmpty(message="Username cannot be empty")
    @Size(min=4, max=15, message = "Username must be between 4 and 15 characters")
    private String username;

    //@JsonIgnore
    //@Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,15})",flags={Pattern.Flag.CASE_INSENSITIVE}, message="Password must contain: at least one digit(0-9), lowercase character, uppercase character. Length: 6-15 characters!")
    private String password;

    @Email(message = "Email should be valid")
    private String email;


    @JoinColumn(name = "role_id")
    @ManyToOne
    private Role role;
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId", referencedColumnName = "id", nullable = false)
    private Role role;
*/
  /* public Long getRoleId() {
       return roleId;
   }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
*/
    //FK
  //  private Long roleId;

    protected User() {}

    public User(String firstName,String lastName,String username,String password,String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = null;
    }

    public User(String firstName,String lastName,String username,String password,String email,Role role){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
/*
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
*/
    @Override
    public String toString() {
        return String.format(
                "User[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }




}
