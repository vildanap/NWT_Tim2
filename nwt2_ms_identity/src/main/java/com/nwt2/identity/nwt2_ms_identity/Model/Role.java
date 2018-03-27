package com.nwt2.identity.nwt2_ms_identity.Model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_generator")
    @SequenceGenerator(name="role_generator", sequenceName = "role_seq", allocationSize=1)
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

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
}
