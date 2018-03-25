package com.nwt2.location.nwt2_ms_location.Controller;

/**
 * Created by Dragnic on 3/25/2018.
 */
public class FieldErrorDTO {

    private String field;

    private String message;

    public FieldErrorDTO(String field, String message) {
        this.setField(field);
        this.setMessage(message);
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //Getters are omitted.
}