package com.nwt2.like.nwt2_ms_like.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by amina on 3/20/2018.
 */
@Entity
public class ReviewPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // Foreing keys
    private Integer ReviewId;
    private Integer PhotoId;

    // constructor
    public ReviewPhoto(Integer ReviewId, Integer PhotoId) {
        this.ReviewId=ReviewId;
        this.PhotoId=PhotoId;
    }
}
