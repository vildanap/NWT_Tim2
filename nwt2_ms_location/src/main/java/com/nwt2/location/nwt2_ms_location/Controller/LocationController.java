package com.nwt2.location.nwt2_ms_location.Controller;

import com.nwt2.location.nwt2_ms_location.Model.Location;
import com.nwt2.location.nwt2_ms_location.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Dragnic on 3/23/2018.
 */
@RestController
@RequestMapping("/locations")
public class LocationController {
    private final LocationRepository locationRepository;
    @Autowired
    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    Collection<Location> readLocations() {

        return this.locationRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{locationId}")
    Optional<Location> readLocation(@PathVariable Long locationId) {

        return this.locationRepository.findById(locationId);
    }


}
