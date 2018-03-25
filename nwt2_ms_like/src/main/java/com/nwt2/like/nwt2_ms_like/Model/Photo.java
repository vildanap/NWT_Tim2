package com.nwt2.like.nwt2_ms_like.Model;

import javax.persistence.*;

/**
 * Created by amina on 3/20/2018.
 */
@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "photo_generator")
    @SequenceGenerator(name="photo_generator", sequenceName = "photo_seq", allocationSize=1)
    private long id;

    private String url;

    // Default value constructor
    public Photo(String url) {
        this.url=url;
    }

    //JPA only
    private Photo() { }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getId() {
        return id;
    }

}
