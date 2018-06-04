package com.nwt2.review.nwt2_ms_review.Controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.nwt2.review.nwt2_ms_review.ExceptionHandler.JSONExceptionHandler;
import com.nwt2.review.nwt2_ms_review.Model.Location;
import com.nwt2.review.nwt2_ms_review.Model.LocationInfo;
import com.nwt2.review.nwt2_ms_review.Model.Review;
import com.nwt2.review.nwt2_ms_review.Repository.LocationRepository;
import com.nwt2.review.nwt2_ms_review.Repository.ReviewRepository;
import com.nwt2.review.nwt2_ms_review.Services.PhotoEventHandler;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.*;

/**
 * Created by ohrinator on 3/27/18.
 */
@CrossOrigin
@RestController
@RequestMapping("/reviews")
class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private PhotoEventHandler photoEventHandler;

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
        Get most recent reviews
     */
    @RequestMapping(method = RequestMethod.GET, value = "/recent")
    public ResponseEntity<Iterable<Review>> getRecentReviews() {
        Iterable<Review> reviews = this.reviewRepository.findTop10ByOrderByIdDesc();

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
      Get reviews from a single user
      @params: integer (user)
      @return: ResponseEntity<Iterable<Review>>
   */
    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}")
    public ResponseEntity<Iterable<Review>> getUserReviews(@PathVariable Integer userId) {
        Iterable<Review> reviews = this.reviewRepository.findByUserId(userId);

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

        Review review = selectedReview.get();

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


        // List of images
        List<String> images = review.getImages();

        // Save the review
        this.reviewRepository.save(review);

        // Fetch the reveiw ID
        Long id = review.getId();

        // Communicate the images to the MS controller
        for(String image : images) {
            photoEventHandler.handleAfterCreated(image, id);
        }

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

    /*
       Like review
       @params: Integer
       @return: ResponseEntity
    */
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}/like")
    public ResponseEntity<?> like(@PathVariable("id") Long id) {
        Optional<Review> selectedReview = reviewRepository.findById(id);

        if(!selectedReview.isPresent()) {
            return new ResponseEntity<JSONObject>(
                    JSONExceptionHandler.getErrorObject("No entity with ID: " + id, HttpStatus.NOT_FOUND.value()),
                    HttpStatus.NOT_FOUND
            );
        }

        if(selectedReview.get().getNumberOfLikes()==null) {
            selectedReview.get().setNumberOfLikes(1);
        }
        else {
            selectedReview.get().setNumberOfLikes(selectedReview.get().getNumberOfLikes() + 1);
        }
        reviewRepository.save(selectedReview.get());

        return new ResponseEntity<Review>(selectedReview.get(), HttpStatus.OK);
    }

    /*
      Dislike review
      @params: Integer
      @return: ResponseEntity
   */
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}/dislike")
    public ResponseEntity<?> dislike(@PathVariable("id") Long id) {
        Optional<Review> selectedReview = reviewRepository.findById(id);

        if(!selectedReview.isPresent()) {
            return new ResponseEntity<JSONObject>(
                    JSONExceptionHandler.getErrorObject("No entity with ID: " + id, HttpStatus.NOT_FOUND.value()),
                    HttpStatus.NOT_FOUND
            );
        }

        if(selectedReview.get().getNumberOfDislikes()==null){
            selectedReview.get().setNumberOfDislikes(1);
        }
        else {
            selectedReview.get().setNumberOfDislikes(selectedReview.get().getNumberOfDislikes() + 1);
        }
        reviewRepository.save(selectedReview.get());

        return new ResponseEntity<Review>(selectedReview.get(), HttpStatus.OK);
    }

    /*
        Get top 6 locations by number of reviews
        @return: ResponseEntity<Iterable<Review>>
     */
    @RequestMapping(method = RequestMethod.GET, value = "/toplocations")
    public ResponseEntity<Iterable<Location>> getTopLocations() {
       Iterable<Location> locations = this.locationRepository.findFirst6ByOrderByIdDesc();//locationRepository.findAll();
       /* Map<Long, Long> map = new HashMap<Long, Long>();

        List<Location> topLocations = new ArrayList<Location>();

        for (Location l : locations) {
            map.put(l.getId(), this.reviewRepository.countByCityId(l.getId()));
        }*/
        //TODO

        return new ResponseEntity<Iterable<Location>>(locations, HttpStatus.OK);
    }

    /*
        Get info about location (number of reviews and average grade)
     */
    @RequestMapping(method = RequestMethod.GET, value = "location/info/{locationId}")
    public ResponseEntity<LocationInfo> getInfoAboutLocation(@PathVariable("locationId") Integer locationId) {

        Long numberOfReviews = Long.valueOf(0);
        Double averageGrade = 0.0;
        if(this.reviewRepository.existsByCityId(locationId)) {
            numberOfReviews = this.reviewRepository.countByCityId(locationId);
            averageGrade = this.reviewRepository.getAverageGrade(locationId);
        }

        if(numberOfReviews.equals(null) || numberOfReviews.equals("undefined"))numberOfReviews = Long.valueOf(0);
        if(averageGrade.equals(null) || averageGrade.equals("undefined"))averageGrade = 0.0;

        LocationInfo locationInfo = new LocationInfo(numberOfReviews, averageGrade);
        return new ResponseEntity<LocationInfo>(locationInfo, HttpStatus.OK);
    }




}
