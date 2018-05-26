package com.nwt2.review.nwt2_ms_review.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by ohrinator on 4/3/18.
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {    @Id
private Long id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 5, max = 200, message = "Name must be between 5 and 200 characters")
    private String name;

    protected Location() {}

    public Location(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
