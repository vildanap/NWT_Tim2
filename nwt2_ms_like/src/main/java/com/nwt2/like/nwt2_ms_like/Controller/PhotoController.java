package com.nwt2.like.nwt2_ms_like.Controller;
import com.nwt2.like.nwt2_ms_like.Model.Photo;
import com.nwt2.like.nwt2_ms_like.Repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by amina on 3/25/2018.
 */
@RestController
@RequestMapping("/photos")
public class PhotoController {
    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoController(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    // ------------------- Get all photos ----------------------------------------
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseEntity<Collection<Photo>> readPhotos() {
        Collection<Photo> photos = this.photoRepository.findAll();
        if(photos.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Collection<Photo>>(photos, HttpStatus.OK);
    }

    // ------------------- Get photo by id ----------------------------------------
    @RequestMapping(method = RequestMethod.GET, value = "/{photoId}")
    public  ResponseEntity<?> readPhoto(@PathVariable Long photoId) {
        Optional<Photo> photo = this.photoRepository.findById(photoId);
        if (!photo.isPresent()) {
            return new ResponseEntity(new CustomErrorType("Photo with id " + photoId + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Photo>>(photo, HttpStatus.OK);
    }

    // ------------------- Create a photo ----------------------------------------
    @RequestMapping(method = RequestMethod.POST, value="/new")
    public ResponseEntity<?> createPhoto(@RequestBody Photo photo, UriComponentsBuilder ucBuilder) {
        if(photoRepository.existsByUrl(photo.getUrl())){
            return new ResponseEntity(new CustomErrorType("Photo with the same url already exists."),
                    HttpStatus.CONFLICT);
        }
        this.photoRepository.save(photo);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/photos/{id}").buildAndExpand(photo.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Delete a photo ----------------------------------------
    @RequestMapping(method = RequestMethod.DELETE, value = "/{photoId}")
    public ResponseEntity<?> deletePhoto(@PathVariable Long photoId) {
        Optional<Photo> photo = this.photoRepository.findById(photoId);
        if (!photo.isPresent()) {
            return new ResponseEntity(new CustomErrorType("Unable to delete. Photo with id " + photoId + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        this.photoRepository.deleteById(photoId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
