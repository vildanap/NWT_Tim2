package com.nwt2.location.nwt2_ms_location.Model;


import javax.persistence.*;

/**
 * Created by Dragnic on 3/20/2018.
 */

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

       private String photoUrl;
    private String name;
    private String description;
    private float latitude;
    private float longitude;


    // Foreing keys

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
