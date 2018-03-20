package com.nwt2.review.nwt2_ms_review.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by ohrinator on 3/20/18.
 */
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer numberOfLikes;
    private Integer numberOfDislikes;
    private String comment;
    private Integer grade;

    // Foreing keys
    private Integer UserId;
    private Integer CityId;
    private Integer ReviewTypeId;

    // All included constructor
    public Review(Integer likes, Integer dislikes, String comment, Integer grade, Integer ReviewTypeId, Integer CityId, Integer UserId) {
        this.numberOfDislikes = dislikes;
        this.numberOfLikes = likes;
        this.comment = comment;
        this.grade = grade;

        this.ReviewTypeId = ReviewTypeId;
        this.CityId = CityId;
        this.UserId = UserId;
    }

    // Default value constructor
    public Review(Integer likes, Integer dislikes, String comment, Integer grade) {
        this.numberOfDislikes = dislikes;
        this.numberOfLikes = likes;
        this.comment = comment;
        this.grade = grade;

        this.ReviewTypeId = 0;
        this.CityId = 0;
        this.UserId = 0;
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
}
