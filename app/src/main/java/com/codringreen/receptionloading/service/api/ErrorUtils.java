package com.codringreen.receptionloading.service.api;

import com.codringreen.receptionloading.model.response.ErrorResponse;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Response;

public class ErrorUtils {

    public static ErrorResponse getErrorResponse(Response response) {
        ErrorResponse errorResponse = new ErrorResponse();
        try {
            return (ErrorResponse) new Gson().fromJson(response.errorBody().string(), ErrorResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
            return errorResponse;
        }
    }
}