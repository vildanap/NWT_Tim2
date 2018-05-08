package com.nwt2.review.nwt2_ms_review.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ohrinator on 3/20/18.
 */
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer numberOfLikes;
    private Integer numberOfDislikes;
    private String comment;
    private Integer grade;

    private Integer userId;
    private Integer cityId;
    private Integer reviewTypeId;

    @Transient
    private List<String> images;

    // Default constructor
    public Review() {
        this.numberOfLikes = 0;
        this.numberOfDislikes = 0;
    }

    // All included constructor
    public Review(String comment, Integer grade, Integer ReviewTypeId, Integer CityId, Integer UserId) {
        this.numberOfDislikes = 0;
        this.numberOfLikes = 0;
        this.comment = comment;
        this.grade = grade;

        this.reviewTypeId = ReviewTypeId;
        this.cityId = CityId;
        this.userId = UserId;
    }

    // Default value constructor
    public Review(String comment, Integer grade) {
        this.numberOfDislikes = 0;
        this.numberOfLikes = 0;
        this.comment = comment;
        this.grade = grade;

        this.reviewTypeId = 0;
        this.cityId = 0;
        this.userId = 0;
    }

    public Integer getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(Integer numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public Integer getNumberOfDislikes() {
        return numberOfDislikes;
    }

    public void setNumberOfDislikes(Integer numberOfDislikes) {
        this.numberOfDislikes = numberOfDislikes;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getReviewTypeId() {
        return reviewTypeId;
    }

    public void setReviewTypeId(Integer reviewTypeId) {
        this.reviewTypeId = reviewTypeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
