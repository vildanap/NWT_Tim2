package com.nwt2.review.nwt2_ms_review.Controller;

import com.nwt2.review.nwt2_ms_review.ExceptionHandler.JSONExceptionHandler;
import com.nwt2.review.nwt2_ms_review.Model.ReviewType;
import com.nwt2.review.nwt2_ms_review.Repository.ReviewTypeRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.Optional;

/**
 * Created by ohrinator on 3/27/18.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/types")
public class ReviewTypeController {

    @Autowired
    private ReviewTypeRepository reviewTypeRepository;

    /*
        Get all review types
        @params: /
        @return: ResponseEntity<Iterable<ReviewType>>
     */
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseEntity<Iterable<ReviewType>> getAllReviews() {
        Iterable<ReviewType> reviews = this.reviewTypeRepository.findAll();

        return new ResponseEntity<Iterable<ReviewType>>(reviews, HttpStatus.OK);
    }

    /*
        Get a single review type
        @param: Long
        @return: ResponseEntity
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> show(@PathVariable("id") Long id) {
        Optional<ReviewType> selectedReviewType = reviewTypeRepository.findById(id);

        if(!selectedReviewType.isPresent()) {
            return new ResponseEntity<JSONObject>(
                    JSONExceptionHandler.getErrorObject("No entity with ID: " + id, HttpStatus.NOT_FOUND.value()),
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<ReviewType>(selectedReviewType.get(), HttpStatus.OK);
    }

    /*
        Create new review type
        @param: ReviewType
        @return: ResponseEntity
     */
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<?> store(@RequestBody ReviewType reviewType) {
        boolean error = false;

        JSONObject errors = new JSONObject();

        if(reviewType.getName() == null) {
            errors.put("error_name", "The name parameter is missing.");
            error = true;
        }

        if(error) {
            return new ResponseEntity<JSONObject>(
                    JSONExceptionHandler.getErrorObject(errors, HttpStatus.BAD_REQUEST.value()),
                    HttpStatus.BAD_REQUEST
            );
        }

        this.reviewTypeRepository.save(reviewType);
        return new ResponseEntity<ReviewType>(reviewType, HttpStatus.CREATED);
    }

    /*
        Update review type
        @parms: Integer, ReviewType
        @return: ResponseEntity
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ReviewType reviewType) {
        Optional<ReviewType> selectedReviewType = reviewTypeRepository.findById(id);

        if(!selectedReviewType.isPresent()) {
            return new ResponseEntity<JSONObject>(
                    JSONExceptionHandler.getErrorObject("No entity with ID: " + id, HttpStatus.NOT_FOUND.value()),
                    HttpStatus.NOT_FOUND
            );
        }

        boolean error = false;

        JSONObject errors = new JSONObject();

        if(reviewType.getName() == null) {
            errors.put("error_name", "The name parameter is missing.");
            error = true;
        }

        if(error) {
            return new ResponseEntity<JSONObject>(
                    JSONExceptionHandler.getErrorObject(errors, HttpStatus.BAD_REQUEST.value()),
                    HttpStatus.BAD_REQUEST
            );
        }

        selectedReviewType.get().setName(reviewType.getName());

        reviewTypeRepository.save(selectedReviewType.get());

        return new ResponseEntity<ReviewType>(selectedReviewType.get(), HttpStatus.OK);
    }

    /*
        Delete review type
        @params: Integer
        @return: ResponseEntity
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<?> destroy(@PathVariable("id") Long id) {
        Optional<ReviewType> selectedReviewType = reviewTypeRepository.findById(id);

        if(!selectedReviewType.isPresent()) {
            return new ResponseEntity<JSONObject>(
                    JSONExceptionHandler.getErrorObject("No entity with ID: " + id, HttpStatus.NOT_FOUND.value()),
                    HttpStatus.NOT_FOUND
            );
        }

        reviewTypeRepository.deleteById(id);
        return new ResponseEntity<ReviewType>(HttpStatus.NO_CONTENT);
    }


}
