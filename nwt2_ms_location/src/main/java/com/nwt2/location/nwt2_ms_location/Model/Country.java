package com.nwt2.location.nwt2_ms_location.Model;

import javax.persistence.*;

/**
 * Created by Dragnic on 3/20/2018.
 */
@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_generator")
    @SequenceGenerator(name="country_generator", sequenceName = "country_seq", allocationSize=1)
    private long id;

    private String name;



    public Country(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
