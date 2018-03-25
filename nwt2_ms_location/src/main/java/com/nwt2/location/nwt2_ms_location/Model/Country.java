package com.nwt2.location.nwt2_ms_location.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Dragnic on 3/20/2018.
 */
@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_generator")
    @SequenceGenerator(name="country_generator", sequenceName = "country_seq", allocationSize=1)
    private long id;
    @NotNull(message = "Name cannot be null")
    @Size(min = 5, max = 200, message = "Name must be between 5 and 200 characters")
    private String name;

    public Country(){} // JPA only

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }
}
