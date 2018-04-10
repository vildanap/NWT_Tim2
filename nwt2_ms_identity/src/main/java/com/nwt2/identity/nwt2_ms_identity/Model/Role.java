package com.nwt2.identity.nwt2_ms_identity.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = Role.class)
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_generator")
    @SequenceGenerator(name="role_generator", sequenceName = "role_seq", allocationSize=1)
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 5, max = 200, message = "Name must be between 5 and 200 characters")
    private String name;

   // @OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
   // private List<User> users =  new ArrayList<>();
   @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)

   private Set<User> users;
    protected  Role() {}

    public Role(String name){

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "Role[id=%d, name='%s']",
                id, name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
