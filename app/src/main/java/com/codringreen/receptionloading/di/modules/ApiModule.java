package com.codringreen.receptionloading.di.modules;

import android.annotation.SuppressLint;

import com.codringreen.receptionloading.BuildConfig;
import com.codringreen.receptionloading.managers.interceptors.BasicAuthInterceptor;
import com.codringreen.receptionloading.managers.interceptors.BasicTokenInterceptor;
import com.codringreen.receptionloading.service.api.IAuthApiService;
import com.codringreen.receptionloading.service.api.IReceptionLoadingApiService;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides
    OkHttpClient provideOkhttpClient() {
        try {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(httpLoggingInterceptor);
            builder.addInterceptor(new BasicTokenInterceptor());
            builder.connectTimeout(300L, TimeUnit.SECONDS);
            builder.readTimeout(300L, TimeUnit.SECONDS);
            return builder.build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BuildConfig.INVENTORY_BASE_URL)
                .client(okHttpClient).build();
    }

    @Provides
    IAuthApiService provideAuthApi(Retrofit retrofit) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(httpLoggingInterceptor);
//        if (PreferenceManager.INSTANCE.isLoggedIn().booleanValue()) {
//            builder.addInterceptor(new BasicTokenInterceptor());
//        } else {
        builder.addInterceptor(new BasicAuthInterceptor());
        //     }
        builder.connectTimeout(300L, TimeUnit.SECONDS);
        builder.readTimeout(300L, TimeUnit.SECONDS);
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BuildConfig.INVENTORY_BASE_URL).client(builder.build()).build().create(IAuthApiService.class);
    }

//    private static OkHttpClient getUnsafeOkHttpClient() {
//        try {
//            @SuppressLint("CustomX509TrustManager") TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: com.codringreen.receptionloading.di.modules.ApiModule.1
//                @SuppressLint("TrustAllX509TrustManager")
//                @Override // javax.net.ssl.X509TrustManager
//                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//                }
//
//                @SuppressLint("TrustAllX509TrustManager")
//                @Override // javax.net.ssl.X509TrustManager
//                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//                }
//
//                @Override // javax.net.ssl.X509TrustManager
//                public X509Certificate[] getAcceptedIssuers() {
//                    return new X509Certificate[0];
//                }
//            }};
//            SSLContext sSLContext = SSLContext.getInstance("SSL");
//            sSLContext.init(null, trustManagerArr, new SecureRandom());
//            SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
//            OkHttpClient.Builder builder = new OkHttpClient.Builder();
//            builder.sslSocketFactory(socketFactory);
//            builder.addInterceptor(new BasicTokenInterceptor());
//            builder.hostnameVerifier(new HostnameVerifier() { // from class: com.codringreen.receptionloading.di.modules.ApiModule.2
//                @SuppressLint("BadHostnameVerifier")
//                @Override // javax.net.ssl.HostnameVerifier
//                public boolean verify(String hostname, SSLSession session) {
//                    return true;
//                }
//            });
//            return builder.build();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Provides
    @Singleton
    IReceptionLoadingApiService provideIReceptionLoadingApiService(Retrofit retrofit) {
        return retrofit.create(IReceptionLoadingApiService.class);
    }
}
