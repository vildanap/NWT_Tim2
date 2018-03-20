package com.nwt2.like.nwt2_ms_like.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by amina on 3/20/2018.
 */
@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String url;

    // Default value constructor
    public Photo(String url) {
        this.url=url;
    }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

}
