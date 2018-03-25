package com.nwt2.like.nwt2_ms_like.Controller;

import org.springframework.util.MultiValueMap;

public class CustomErrorType {

    private String errorMessage;

    public CustomErrorType(String errorMessage) {
        this.errorMessage=errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
