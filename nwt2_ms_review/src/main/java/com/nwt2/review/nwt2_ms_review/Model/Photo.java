package com.nwt2.review.nwt2_ms_review.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Photo {
    @Id
    private Long id;

    @NotNull
    @Pattern(regexp = "^https?://(?:[a-z0-9\\-]+\\.)+[a-z]{2,6}(?:/[^/#?]+)+\\.(?:jpg|gif|png)$", message = "Invalid URL")
    private String url;

    protected Photo() {}

    public Photo(String url) {
        this.url=url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
