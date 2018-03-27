package com.nwt2.identity.nwt2_ms_identity.Controller;

import org.springframework.util.MultiValueMap;

public class CustomErrorType {

    private String errorMessage;

    public CustomErrorType(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
