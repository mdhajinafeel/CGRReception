package com.codringreen.receptionloading.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;

import com.codringreen.receptionloading.R;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.codringreen.receptionloading.model.response.DownloadMaserResponse;
import com.codringreen.receptionloading.repository.MasterRepository;
import com.codringreen.receptionloading.service.api.ResponseCallBack;
import com.codringreen.receptionloading.utils.NetworkConnectivity;

import javax.inject.Inject;

public class DashboardViewModel extends BaseViewModel {

    @SuppressLint("StaticFieldLeak")
    private Context context;
    private String errorTitle;
    private final NetworkConnectivity networkConnectivity;
    private final MasterRepository masterRepository;
    private final MutableLiveData<Boolean> progressState = new MutableLiveData<>();
    private final MutableLiveData<Boolean> downloadState = new MutableLiveData<>();
    private final MutableLiveData<Boolean> syncStatus = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    @Inject
    public DashboardViewModel(NetworkConnectivity networkConnectivity, Context context, MasterRepository masterRepository) {
        this.networkConnectivity = networkConnectivity;
        this.context = context;
        this.masterRepository = masterRepository;
    }

    public void getMasterDownload() {
        try {
            if (networkConnectivity.isNetworkAvailable()) {
                progressState.postValue(true);
                Thread.sleep(1000L);
                downloadMaster(false);
            } else {
                setErrorTitle(context.getString(R.string.information));
                progressState.postValue(false);
                errorMessage.postValue(context.getString(R.string.no_internet));
            }
        } catch (Exception e) {
            e.printStackTrace();
            setErrorTitle(context.getString(R.string.error));
            progressState.postValue(false);
            errorMessage.postValue(e.getMessage());
        }
    }

    public void downloadMaster(boolean isSync) {
        masterRepository.masterDownload(new ResponseCallBack<DownloadMaserResponse>() {
            @Override
            public void onSuccess(DownloadMaserResponse data) {
                progressState.postValue(false);
                if (data != null) {
                    if (data.isStatus()) {
                        if (!isSync) {
                            downloadState.postValue(true);
                            return;
                        }
                        setErrorTitle(context.getString(R.string.information));
                        syncStatus.postValue(true);
                        return;
                    }
                    if (isSync) {
                        syncStatus.postValue(false);
                    } else {
                        downloadState.postValue(false);
                    }
                    return;
                }
                setErrorTitle(context.getString(R.string.error));
                errorMessage.postValue(context.getString(R.string.common_error));
            }

            @Override
            public void onError(String message) {
                setErrorTitle(context.getString(R.string.error));
                progressState.postValue(false);
                errorMessage.postValue(message);
            }
        });
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    public LiveData<String> getError() {
        return errorMessage;
    }

    public LiveData<Boolean> getProgressBar() {
        return progressState;
    }

    public LiveData<Boolean> getDownloadState() {
        return downloadState;
    }
}