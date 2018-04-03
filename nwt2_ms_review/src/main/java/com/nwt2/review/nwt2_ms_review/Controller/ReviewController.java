package com.nwt2.review.nwt2_ms_review.Controller;

import com.nwt2.review.nwt2_ms_review.ExceptionHandler.JSONExceptionHandler;
import com.nwt2.review.nwt2_ms_review.Model.Review;
import com.nwt2.review.nwt2_ms_review.Repository.ReviewRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

/**
 * Created by ohrinator on 3/27/18.
 */
@RestController
@RequestMapping("/reviews")
class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    /*
        Get all reviews
        @params: /
        @return: ResponseEntity<Iterable<Review>>
     */
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseEntity<Iterable<Review>> getAllReviews() {
        Iterable<Review> reviews = this.reviewRepository.findAll();

        return new ResponseEntity<Iterable<Review>>(reviews, HttpStatus.OK);
    }

    /*
        Get reviews from a single location
        @params: integer (location)
        @return: ResponseEntity<Iterable<Review>>
     */
    @RequestMapping(method = RequestMethod.GET, value = "/location/{cityId}")
    public ResponseEntity<Iterable<Review>> getLocationReviews(@PathVariable Integer cityId) {
        Iterable<Review> reviews = this.reviewRepository.findByCityId(cityId);

        return new ResponseEntity<Iterable<Review>>(reviews, HttpStatus.OK);
    }

    /*
        Get a single review
        @params: Long
        @return: ResponseEntity
     */
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<Review> selectedReview = reviewRepository.findById(id);

        if(!selectedReview.isPresent()) {
            return new ResponseEntity<JSONObject>(
                    JSONExceptionHandler.getErrorObject("No entity with ID: " + id, HttpStatus.NOT_FOUND.value()),
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<Review>(selectedReview.get(), HttpStatus.OK);
    }

    /*
        Create a new review
        @params: Review
        @return: ResponseEntity
     */
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<?> store(@RequestBody Review review) {
        boolean error = false;

        JSONObject errors = new JSONObject();

        if(review.getComment() == null) {
            errors.put("error_comment", "The comment parameter is missing.");
            error = true;
        }

        if(review.getGrade() == null) {
            errors.put("error_grade", "The grade parameter is missing.");
            error = true;
        }

        if(review.getCityId() == null) {
            errors.put("error_location", "The location ID is missing.");
            error = true;
        }

        if(review.getReviewTypeId() == null) {
            errors.put("error_type", "The review type ID is missing.");
            error = true;
        }

        if(review.getUserId() == null) {
            errors.put("error_user", "The user ID is missing");
            error = true;
        }

        if(error) {
            return new ResponseEntity<JSONObject>(
                    JSONExceptionHandler.getErrorObject(errors, HttpStatus.BAD_REQUEST.value()),
                    HttpStatus.BAD_REQUEST
            );
        }

        this.reviewRepository.save(review);
        return new ResponseEntity<Review>(review, HttpStatus.CREATED);
    }

    /*
        Update review
        @params: Integer, Review
        @return: ResponseEntity
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Review review) {
        Optional<Review> selectedReview = reviewRepository.findById(id);

        if(!selectedReview.isPresent()) {
            return new ResponseEntity<JSONObject>(
                    JSONExceptionHandler.getErrorObject("No entity with ID: " + id, HttpStatus.NOT_FOUND.value()),
                    HttpStatus.NOT_FOUND
            );
        }

        boolean error = false;

        JSONObject errors = new JSONObject();

        if(review.getComment() == null) {
            errors.put("error_comment", "The comment parameter is missing.");
            error = true;
        }

        if(review.getGrade() == null) {
            errors.put("error_grade", "The grade parameter is missing.");
            error = true;
        }

        if(review.getCityId() == null) {
            errors.put("error_location", "The location ID is missing.");
            error = true;
        }

        if(review.getReviewTypeId() == null) {
            errors.put("error_type", "The review type ID is missing.");
            error = true;
        }

        if(review.getUserId() == null) {
            errors.put("error_user", "The user ID is missing");
            error = true;
        }

        if(error) {
            return new ResponseEntity<JSONObject>(
                    JSONExceptionHandler.getErrorObject(errors, HttpStatus.BAD_REQUEST.value()),
                    HttpStatus.BAD_REQUEST
            );
        }

        selectedReview.get().setComment(review.getComment());
        selectedReview.get().setGrade(review.getGrade());
        selectedReview.get().setReviewTypeId(review.getReviewTypeId());

        reviewRepository.save(selectedReview.get());

        return new ResponseEntity<Review>(selectedReview.get(), HttpStatus.OK);
    }

    /*
        Delete review
        @params: Integer
        @return: ResponseEntity
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<?> destroy(@PathVariable("id") Long id) {
        Optional<Review> selectedReview = reviewRepository.findById(id);

        if(!selectedReview.isPresent()) {
            return new ResponseEntity<JSONObject>(
                    JSONExceptionHandler.getErrorObject("No entity with ID: " + id, HttpStatus.NOT_FOUND.value()),
                    HttpStatus.NOT_FOUND
            );
        }

        reviewRepository.deleteById(id);
        return new ResponseEntity<Review>(HttpStatus.NO_CONTENT);
    }

}
