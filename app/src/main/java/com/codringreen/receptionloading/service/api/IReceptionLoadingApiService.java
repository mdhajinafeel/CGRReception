package com.codringreen.receptionloading.service.api;

import com.codringreen.receptionloading.constants.IAPIConstants;
import com.codringreen.receptionloading.model.response.DownloadMaserResponse;
import com.codringreen.receptionloading.model.response.OriginResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface IReceptionLoadingApiService {
    @GET(IAPIConstants.ORIGIN)
    Call<OriginResponse> gerOrigins(@Header("Content-Type") String content_type);

    @GET(IAPIConstants.MASTERDOWNLOAD)
    Call<DownloadMaserResponse> masterDownload(@Header("Content-Type") String content_type);
}