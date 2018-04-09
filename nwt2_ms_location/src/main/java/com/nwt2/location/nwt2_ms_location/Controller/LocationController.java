package com.nwt2.location.nwt2_ms_location.Controller;

import com.nwt2.location.nwt2_ms_location.Model.Location;
import com.nwt2.location.nwt2_ms_location.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorDTO processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        return processFieldErrors(fieldErrors);
    }

    private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
        ValidationErrorDTO dto = new ValidationErrorDTO();

        for (FieldError fieldError: fieldErrors) {
            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
        }

        return dto;
    }

    private String resolveLocalizedErrorMessage(FieldError fieldError) {


        //If the message was not found, return the most accurate field error code instead.
        //You can remove this check if you prefer to get the default error message.

            String fieldErrorCodes = fieldError.getDefaultMessage();


        return fieldErrorCodes;
    }

    // -------------------Retrieve All Locations---------------------------------------------
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseEntity<List<Location>> listAllLocations() {
        List<Location> locations = locationRepository.findAll();
        if (locations.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Location>>(locations, HttpStatus.OK);
    }

    // -------------------Retrieve One Location---------------------------------------------
    @RequestMapping(method = RequestMethod.GET, value = "/{locationId}")
    ResponseEntity<?> getLocation (@PathVariable Long locationId) {

        Optional<Location> location = this.locationRepository.findById(locationId);
        if (!location.isPresent()) {
            return new ResponseEntity(new CustomErrorType("Location with id " + locationId
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Location>>(location, HttpStatus.OK);
    }

    // -------------------Create a Location --------------------------------------------------
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<?> createLocation(@Valid @RequestBody Location location, UriComponentsBuilder ucBuilder) {

        if (locationRepository.existsByName(location.getName())) {
            return new ResponseEntity(new CustomErrorType("Unable to create. A Location with name " +
                    location.getName() + " already exist."),HttpStatus.CONFLICT);
        }

        locationRepository.save(location);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/locations/{id}").buildAndExpand(location.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Location ------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateLocation(@PathVariable("id") long id, @Valid @RequestBody Location location) {

        Optional<Location> currentLocation = locationRepository.findById(id);

        if (!currentLocation.isPresent()) {
            return new ResponseEntity(new CustomErrorType("Unable to update. Location with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentLocation.get().setDescription(location.getDescription());
        currentLocation.get().setCountry(location.getCountry());
        currentLocation.get().setLatitude(location.getLatitude());
        currentLocation.get().setLongitude(location.getLongitude());
        currentLocation.get().setName(location.getName());
        currentLocation.get().setPhotoUrl(location.getPhotoUrl());

        locationRepository.save(currentLocation.get());
        return new ResponseEntity<Optional<Location>>(currentLocation, HttpStatus.OK);
    }

    // ------------------- Delete a Location-----------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteLocation(@PathVariable("id") long id) {

        Optional<Location> location = locationRepository.findById(id);
        if (!location.isPresent()) {
            return new ResponseEntity(new CustomErrorType("Unable to delete. Location with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        locationRepository.deleteById(id);
        return new ResponseEntity<Location>(HttpStatus.NO_CONTENT);
    }
// ------------------- Delete All Locations-----------------------------

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Location> deleteAllLocations() {

        locationRepository.deleteAll();
        return new ResponseEntity<Location>(HttpStatus.NO_CONTENT);
    }

}

