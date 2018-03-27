package com.nwt2.review.nwt2_ms_review.ExceptionHandler;

import net.minidev.json.JSONObject;

/**
 * Created by ohrinator on 3/27/18.
 */
public class JSONExceptionHandler {
    public static JSONObject getErrorObject(String errorMessage, Integer errorCode) {
        JSONObject error = new JSONObject();

        error.put("status_code", errorCode);
        error.put("message", errorMessage);

        return error;
    }

    public static JSONObject getErrorObject(JSONObject errorMessage, Integer errorCode) {
        JSONObject error = new JSONObject();

        error.put("status_code", errorCode);
        error.put("message", errorMessage);

        return error;
    }
}
