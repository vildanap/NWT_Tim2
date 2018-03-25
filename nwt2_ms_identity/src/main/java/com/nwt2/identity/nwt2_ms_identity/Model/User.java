package com.nwt2.identity.nwt2_ms_identity.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @JsonIgnore
    private String password;

    @Email(message = "Email should be valid")
    private String email;

    //FK
    private Integer roleId;


    protected User() {}

    public User(String firstName,String lastName,String username,String password,String email,Integer roleId){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roleId = roleId;
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

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}
