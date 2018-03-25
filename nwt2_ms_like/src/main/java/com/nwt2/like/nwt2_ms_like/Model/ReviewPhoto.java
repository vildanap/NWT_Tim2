package com.nwt2.like.nwt2_ms_like.Model;

import javax.persistence.*;

/**
 * Created by amina on 3/20/2018.
 */
@Entity
public class ReviewPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_photo_generator")
    @SequenceGenerator(name="review_photo_generator", sequenceName = "review_photo_seq", allocationSize=1)
    private long id;

    // Foreing keys
    private long reviewId;
    private long photoId;

    // constructor
    public ReviewPhoto(long ReviewId, long PhotoId) {
        this.reviewId=ReviewId;
        this.photoId=PhotoId;
    }

    //JPA only
    public ReviewPhoto(){}

    public long getId() {
        return id;
    }

    public long getReviewId() {
        return reviewId;
    }

    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
    }

    public long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(long photoId) {
        this.photoId = photoId;
    }
}
