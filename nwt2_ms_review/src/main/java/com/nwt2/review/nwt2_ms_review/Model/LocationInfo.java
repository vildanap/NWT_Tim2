package com.nwt2.review.nwt2_ms_review.Model;

public class LocationInfo {

    public Long getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(Long numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    private Long numberOfReviews;

    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    private Double averageGrade;

    public LocationInfo(Long numberOfReviews, Double averageGrade) {
        this.numberOfReviews = numberOfReviews;
        this.averageGrade = averageGrade;
    }

}
