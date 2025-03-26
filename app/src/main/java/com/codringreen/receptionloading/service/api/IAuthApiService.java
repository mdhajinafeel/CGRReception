package com.codringreen.receptionloading.service.api;

import com.codringreen.receptionloading.constants.IAPIConstants;
import com.codringreen.receptionloading.model.request.LoginRequest;
import com.codringreen.receptionloading.model.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IAuthApiService {

    @POST(IAPIConstants.LOGIN)
    Call<LoginResponse> postLogin(@Body LoginRequest loginRequest);
}