package com.nwt2.location.nwt2_ms_location.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragnic on 3/25/2018.
 */
public class ValidationErrorDTO {

    private List<FieldErrorDTO> fieldErrors = new ArrayList<>();

    public ValidationErrorDTO() {

    }

    public void addFieldError(String path, String message) {
        FieldErrorDTO error = new FieldErrorDTO(path, message);
        getFieldErrors().add(error);
    }

    public List<FieldErrorDTO> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldErrorDTO> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    //Getter is omitted.
}