package com.codringreen.receptionloading.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.codringreen.receptionloading.R;
import com.codringreen.receptionloading.helper.PreferenceManager;
import com.codringreen.receptionloading.model.request.LoginRequest;
import com.codringreen.receptionloading.model.response.LoginResponse;
import com.codringreen.receptionloading.model.response.OriginDataResponse;
import com.codringreen.receptionloading.repository.LoginRepository;
import com.codringreen.receptionloading.repository.MasterRepository;
import com.codringreen.receptionloading.service.api.ResponseCallBack;
import com.codringreen.receptionloading.utils.NetworkConnectivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class LoginViewModel extends BaseViewModel {

    @SuppressLint("StaticFieldLeak")
    private Context context;
    private String errorTitle;
    private final LoginRepository loginRepository;
    private final MasterRepository masterRepository;
    private final NetworkConnectivity networkConnectivity;
    private int originId;
    public List<OriginDataResponse> origins;
    private String password;
    private OriginDataResponse selectedLoginOrigin;
    private String userName;
    private final MutableLiveData<Boolean> loginBtnState = new MutableLiveData<>();
    private final MutableLiveData<Boolean> progressState = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loginStatus = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> originStatus = new MutableLiveData<>();

    @Inject
    public LoginViewModel(NetworkConnectivity networkConnectivity, LoginRepository loginRepository, MasterRepository masterRepository, Context context) {
        this.context = context;
        this.networkConnectivity = networkConnectivity;
        this.loginRepository = loginRepository;
        this.masterRepository = masterRepository;
    }

    public void getOrigins() {
        try {
            if (this.networkConnectivity.isNetworkAvailable()) {
                progressState.postValue(true);
                this.masterRepository.getOrigins(new ResponseCallBack<List<OriginDataResponse>>() {
                    @Override
                    public void onSuccess(List<OriginDataResponse> data) {
                        progressState.postValue(false);
                        origins = new ArrayList<>();
                        origins.addAll(data);
                        originStatus.postValue(true);
                    }

                    @Override
                    public void onError(String message) {
                        originStatus.postValue(false);
                        setErrorTitle(context.getString(R.string.error));
                        progressState.postValue(false);
                        errorMessage.postValue(context.getString(R.string.common_error));
                    }
                });
            } else {
                this.originStatus.postValue(false);
                setErrorTitle(context.getString(R.string.information));
                this.progressState.postValue(false);
                this.errorMessage.postValue(context.getString(R.string.no_internet));
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.originStatus.postValue(false);
            setErrorTitle(context.getString(R.string.error));
            this.progressState.postValue(false);
            this.errorMessage.postValue(e.getMessage());
        }
    }

    public void postLogin() {
        try {
            if (networkConnectivity.isNetworkAvailable()) {
                progressState.postValue(true);
                Thread.sleep(1000L);

                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setUsername(getUserName());
                loginRequest.setPassword(getPassword());
                loginRequest.setOriginId(this.selectedLoginOrigin.getOriginId());
                loginRequest.setRoleId(7);

                this.loginRepository.postLogin(loginRequest, new ResponseCallBack<LoginResponse>() {
                    @Override
                    public void onSuccess(LoginResponse data) {

                        if (data.isStatus()) {
                            PreferenceManager.INSTANCE.setKeyName(data.getData().getFullName());
                            PreferenceManager.INSTANCE.setKeyUserName(data.getData().getUserName());
                            PreferenceManager.INSTANCE.setKeyLoginexpiry(data.getData().getExpiresIn());
                            PreferenceManager.INSTANCE.setKeyPassword(getPassword());
                            PreferenceManager.INSTANCE.setKeyEmailid(data.getData().getEmailId());
                            PreferenceManager.INSTANCE.setKeyContactno(data.getData().getContactNo());
                            PreferenceManager.INSTANCE.setKeyAddress(data.getData().getAddress());
                            PreferenceManager.INSTANCE.setKeyPhoto(data.getData().getProfilePhoto());
                            PreferenceManager.INSTANCE.setKeyUserid(data.getData().getUserId());
                            PreferenceManager.INSTANCE.setKeyOriginId(data.getData().getOriginId());
                            PreferenceManager.INSTANCE.setAccessToken(data.getData().getAccessToken());
                            PreferenceManager.INSTANCE.setKeyRefreshtoken(data.getData().getAccessToken());
                            PreferenceManager.INSTANCE.setLoggedIn(data.isStatus());
                            try {
                                Thread.sleep(1000L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            progressState.postValue(false);
                            loginStatus.postValue(true);
                            return;
                        }
                        setErrorTitle(context.getString(R.string.error));
                        errorMessage.postValue(context.getString(R.string.invalid_user));
                    }

                    @Override
                    public void onError(String message) {
                        setErrorTitle(context.getString(R.string.error));
                        progressState.postValue(false);
                        errorMessage.postValue(context.getString(R.string.invalid_user));
                    }
                });
            } else {
                setErrorTitle(context.getString(R.string.information));
                this.progressState.postValue(false);
                this.errorMessage.postValue(context.getString(R.string.no_internet));
            }
        } catch (Exception e) {
            e.printStackTrace();
            setErrorTitle(context.getString(R.string.error));
            this.progressState.postValue(false);
            this.errorMessage.postValue(e.getMessage());
        }
    }

    private void validateLoginBtnState() {
        if ((getUserName() != null && getUserName().length() > 1) && (getPassword() != null && getPassword().length() > 1) && getOriginId() > 0) {
            this.loginBtnState.postValue(true);
        } else {
            this.loginBtnState.postValue(false);
        }
    }

    private String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        validateLoginBtnState();
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        validateLoginBtnState();
    }

    private int getOriginId() {
        return originId;
    }

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    public LiveData<Boolean> getLoginBtnState() {
        return loginBtnState;
    }

    public LiveData<String> getError() {
        return errorMessage;
    }

    public LiveData<Boolean> getProgressBar() {
        return progressState;
    }

    public LiveData<Boolean> getLoginStatus() {
        return loginStatus;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    public LiveData<Boolean> getOriginStatus() {
        return originStatus;
    }

    public void selectedOrigin(List<OriginDataResponse> originLists, List<OriginDataResponse> originFilteredList, OriginDataResponse origin, int position) {
        this.selectedLoginOrigin = origin;
        if (!originFilteredList.isEmpty()) {
            for (OriginDataResponse originList : originLists) {
                originList.setSelected(false);
            }
            int i = 0;
            while (i < originFilteredList.size()) {
                originFilteredList.get(i).setSelected(i == position);
                i++;
            }
        } else {
            int i2 = 0;
            while (i2 < originLists.size()) {
                originLists.get(i2).setSelected(i2 == position);
                i2++;
            }
        }
        validateLoginBtnState();
    }

}