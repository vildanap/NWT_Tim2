package com.nwt2.location.nwt2_ms_location.Controller;

import com.nwt2.location.nwt2_ms_location.Model.Country;
import com.nwt2.location.nwt2_ms_location.Model.Location;
import com.nwt2.location.nwt2_ms_location.Repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by Dragnic on 3/25/2018.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/countries")
public class CountyController {
    private final CountryRepository countryRepository;

    @Autowired
    public CountyController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
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

    // -------------------Retrieve All Countries---------------------------------------------
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseEntity<List<Country>> listAllCountries() {
        List<Country> countries = (List<Country>) this.countryRepository.findAll();
        if (countries.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Country>>(countries, HttpStatus.OK);
    }

    // -------------------Retrieve One Country---------------------------------------------
    @RequestMapping(method = RequestMethod.GET, value = "/{locationId}")
    ResponseEntity<?> getCountry (@PathVariable Long locationId) {

        Optional<Country> country = this.countryRepository.findById(locationId);
        if (!country.isPresent()) {
            return new ResponseEntity(new CustomErrorType("Country with id " + locationId
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Country>>(country, HttpStatus.OK);
    }

    // -------------------Create a Country --------------------------------------------------
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<?> createLocation(@Valid @RequestBody Country country, UriComponentsBuilder ucBuilder) {

        if (countryRepository.existsByName(country.getName())) {
            return new ResponseEntity(new CustomErrorType("Unable to create. A Country with name " +
                    country.getName() + " already exist."),HttpStatus.CONFLICT);
        }
        countryRepository.save(country);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/countries/{id}").buildAndExpand(country.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Country ------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateLocation(@PathVariable("id") long id,@Valid @RequestBody Country country) {

        Optional<Country> currentCountry = countryRepository.findById(id);

        if (!currentCountry.isPresent()) {
            return new ResponseEntity(new CustomErrorType("Unable to upate. Country with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentCountry.get().setName(country.getName());

        countryRepository.save(currentCountry.get());
        return new ResponseEntity<Optional<Country>>(currentCountry, HttpStatus.OK);
    }

    // ------------------- Delete a Location-----------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCountry(@PathVariable("id") long id) {

        Optional<Country> country = countryRepository.findById(id);
        if (!country.isPresent()) {
            return new ResponseEntity(new CustomErrorType("Unable to delete. Country with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        countryRepository.deleteById(id);
        return new ResponseEntity<Country>(HttpStatus.NO_CONTENT);
    }
// ------------------- Delete All Locations-----------------------------

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Location> deleteAllCountries() {

        countryRepository.deleteAll();
        return new ResponseEntity<Location>(HttpStatus.NO_CONTENT);
    }
}
