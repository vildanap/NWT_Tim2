package com.nwt2.review.nwt2_ms_review.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Created by ohrinator on 4/3/18.
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @Id
    private Long id;

    @NotEmpty(message="Username cannot be empty")
    @Size(min=4, max=15, message = "Username must be between 4 and 15 characters")
    private String username;

    protected User() {}

    public User(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
