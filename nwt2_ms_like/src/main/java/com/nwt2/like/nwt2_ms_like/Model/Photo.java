package com.nwt2.like.nwt2_ms_like.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by amina on 3/20/2018.
 */
@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "photo_generator")
    @SequenceGenerator(name="photo_generator", sequenceName = "photo_seq", allocationSize=1)
    private long id;

    @NotNull
    @Pattern(regexp = "/((([A-Za-z]{3,9}:(?:\\/\\/)?)(?:[-;:&=\\+\\$,\\w]+@)?[A-Za-z0-9.-]+(:[0-9]+)?|(?:www.|[-;:&=\\+\\$,\\w]+@)[A-Za-z0-9.-]+)((?:\\/[\\+~%\\/.\\w-_]*)?\\??(?:[-\\+=&;%@.\\w_]*)#?(?:[\\w]*))?)/", message = "Invalid URL")
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
