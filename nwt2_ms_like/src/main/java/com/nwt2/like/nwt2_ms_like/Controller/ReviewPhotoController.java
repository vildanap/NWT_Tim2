package com.nwt2.like.nwt2_ms_like.Controller;

import com.nwt2.like.nwt2_ms_like.Model.ReviewPhoto;
import com.nwt2.like.nwt2_ms_like.Repository.PhotoRepository;
import com.nwt2.like.nwt2_ms_like.Repository.ReviewPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

/**
 * Created by amina on 3/25/2018.
 */
@RestController
@RequestMapping("/reviewphotos")
public class ReviewPhotoController {
    private final ReviewPhotoRepository reviewPhotoRepository;
    private final PhotoRepository photoRepository;

    @Autowired
    public ReviewPhotoController(ReviewPhotoRepository reviewPhotoRepository, PhotoRepository photoRepository) {
        this.reviewPhotoRepository = reviewPhotoRepository;
        this.photoRepository = photoRepository;
    }

    // ------------------- Get all review photos ----------------------------------------
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseEntity<Collection<ReviewPhoto>> readPhotos() {
        Collection<ReviewPhoto> reviewPhotos = this.reviewPhotoRepository.findAll();
        if(reviewPhotos.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Collection<ReviewPhoto>>(reviewPhotos, HttpStatus.OK);
    }

    // ------------------- Get review photo by id ----------------------------------------
    @RequestMapping(method = RequestMethod.GET, value = "/{reviewPhotoId}")
    public  ResponseEntity<?> readPhoto(@PathVariable Long reviewPhotoId) {
        Optional<ReviewPhoto> reviewPhoto = this.reviewPhotoRepository.findById(reviewPhotoId);
        if (!reviewPhoto.isPresent()) {
            return new ResponseEntity(new CustomErrorType("Review photo with id " + reviewPhotoId + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<ReviewPhoto>>(reviewPhoto, HttpStatus.OK);
    }

    // ------------------- Get all photos for specific review ----------------------------------------
    @RequestMapping(method = RequestMethod.GET, value = "/review/{reviewId}")
    public ResponseEntity<Collection<ReviewPhoto>> readPhotosByReview(@PathVariable Long reviewId) {
        Collection<ReviewPhoto> reviewPhotos = this.reviewPhotoRepository.findAllByReviewId(reviewId);
        if(reviewPhotos.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Collection<ReviewPhoto>>(reviewPhotos, HttpStatus.OK);
    }

    // ------------------- Get all photo urls for specific review as an array -------------------------
    @RequestMapping(method = RequestMethod.GET, value = "/review/{reviewId}/urls")
    public ResponseEntity<Collection<String>> readPhotoUrlsByReview(@PathVariable Long reviewId) {
        Collection<ReviewPhoto> reviewPhotos = this.reviewPhotoRepository.findAllByReviewId(reviewId);
        Collection<String> photoUrls = this.photoRepository.findAllUrls(reviewPhotos);
        if(photoUrls.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Collection<String>>(photoUrls, HttpStatus.OK);
    }

    // ------------------- Create a review photo ----------------------------------------
    @RequestMapping(method = RequestMethod.POST, value="/new")
    public ResponseEntity<?> createReviewPhoto(@Valid @RequestBody ReviewPhoto reviewPhoto, UriComponentsBuilder ucBuilder) {
        if(reviewPhotoRepository.existsByReviewIdAndPhotoId(reviewPhoto.getReviewId(), reviewPhoto.getPhotoId())) {
            return new ResponseEntity(new CustomErrorType("Review Photo with the same parameters already exists."),
                    HttpStatus.CONFLICT);
        }

        this.reviewPhotoRepository.save(reviewPhoto);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/reviewphotos/{id}").buildAndExpand(reviewPhoto.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a review photo ----------------------------------------
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<?> updatePhoto(@Valid @PathVariable("id") long id, @RequestBody ReviewPhoto reviewPhoto) {

        Optional<ReviewPhoto> existingRPhoto = this.reviewPhotoRepository.findById(id);

        if (!existingRPhoto.isPresent()) {
            return new ResponseEntity(new CustomErrorType("Unable to update. Review photo with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        if(reviewPhotoRepository.existsByReviewIdAndPhotoId(reviewPhoto.getReviewId(), reviewPhoto.getPhotoId())) {
            return new ResponseEntity(new CustomErrorType("Review Photo with the same parameters already exists."),
                    HttpStatus.CONFLICT);
        }

        existingRPhoto.get().setReviewId(reviewPhoto.getReviewId());
        existingRPhoto.get().setPhotoId(reviewPhoto.getPhotoId());

        this.reviewPhotoRepository.save(existingRPhoto.get());
        return new ResponseEntity<Optional<ReviewPhoto>>(existingRPhoto, HttpStatus.OK);
    }

    // ------------------- Delete a review photo ----------------------------------------
    @RequestMapping(method = RequestMethod.DELETE, value = "/{reviewPhotoId}")
    public ResponseEntity<?> deletePhoto(@PathVariable Long reviewPhotoId) {
        Optional<ReviewPhoto> reviewPhoto = this.reviewPhotoRepository.findById(reviewPhotoId);
        if (!reviewPhoto.isPresent()) {
            return new ResponseEntity(new CustomErrorType("Unable to delete. Review photo with id " + reviewPhotoId + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        this.reviewPhotoRepository.deleteById(reviewPhotoId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
