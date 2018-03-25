package com.nwt2.location.nwt2_ms_location.Model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Dragnic on 3/20/2018.
 */

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_generator")
    @SequenceGenerator(name="location_generator", sequenceName = "location_seq", allocationSize=1)
    private long id;
    @NotNull(message = "Url cannot be null")
    @Size(min = 5, max = 200, message = "Url must be between 5 and 200 characters")
    private String photoUrl;
    @NotNull(message = "Name cannot be null")
    @Size(min = 5, max = 200, message = "Name must be between 5 and 200 characters")
    private String name;
    @Size(min = 5, max = 200, message = "Description must be between 5 and 200 characters")
    private String description;
    @NotNull(message = "Latitude cannot be null")
    private float latitude;
    @NotNull(message = "Longitude cannot be null")
    private float longitude;


    // Foreing keys
    @NotNull(message = "Country cannot be null")
    private Integer CountryId;

    // All included constructor
    public Location(String photoUrl, String name, String description, float latitude, float longitude, Integer countryId) {
        this.photoUrl = photoUrl;
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        CountryId = countryId;
    }

    private Location() { } // JPA only

    public Long getId() { return id;  }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public Integer getCountryId() {
        return CountryId;
    }

    public void setCountryId(Integer countryId) {
        CountryId = countryId;
    }

}
