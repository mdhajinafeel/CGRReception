package com.codringreen.receptionloading.repository;

import com.codringreen.receptionloading.model.request.LoginRequest;
import com.codringreen.receptionloading.model.response.LoginResponse;
import com.codringreen.receptionloading.service.api.IAuthApiService;
import com.codringreen.receptionloading.service.api.ResponseCallBack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    private final IAuthApiService iAuthApiService;

    public LoginRepository(IAuthApiService iAuthApiService) {
        this.iAuthApiService = iAuthApiService;
    }

    public void postLogin(LoginRequest loginRequest, final ResponseCallBack<LoginResponse> callBack) {
        try {
            this.iAuthApiService.postLogin(loginRequest).enqueue(new Callback<LoginResponse>() { // from class: com.codringreen.receptionloading.repository.LoginRepository.1
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        callBack.onSuccess((LoginResponse) response.body());
                    } else {
                        callBack.onError("ERROR_500");
                    }
                }

                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    callBack.onError(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            callBack.onError(e.getMessage());
        }
    }
}