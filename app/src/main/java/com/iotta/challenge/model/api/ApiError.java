package com.iotta.challenge.model.api;

/**
 * Created by Galya on 07/07/2017.
 */

public class ApiError {

    private int statusCode;
    private String message;

    public ApiError(int status, String strMessage) {
          statusCode = status;
          message = strMessage;
    }

    public ApiError() {
    }

    public int status() {
        return statusCode;
    }

    public String message() {
        return message;
    }
}