package com.nwt2.review.nwt2_ms_review.Controller;

import com.nwt2.review.nwt2_ms_review.Model.Location;
import com.nwt2.review.nwt2_ms_review.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ohrinator on 4/3/18.
 */
@RestController
@RequestMapping("/user")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;
/*
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<?> store(@RequestBody Location location) {
        this.locationRepository.save(location);
        return new ResponseEntity<Location>(location, HttpStatus.CREATED);
    }

    */
}
