package com.nwt2.like.nwt2_ms_like.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by amina on 3/20/2018.
 */
@Entity
public class ReviewPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_photo_generator")
    @SequenceGenerator(name="review_photo_generator", sequenceName = "review_photo_seq", allocationSize=1)
    private long id;

    // Foreign keys
    @NotNull(message = "Review cannot be null")
    private Long reviewId;

    @NotNull(message = "Photo cannot be null")
    private Long photoId;

    // constructor
    public ReviewPhoto(Long ReviewId, Long PhotoId) {
        this.reviewId=ReviewId;
        this.photoId=PhotoId;
    }

    //JPA only
    public ReviewPhoto(){}

    public long getId() {
        return id;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }
}
