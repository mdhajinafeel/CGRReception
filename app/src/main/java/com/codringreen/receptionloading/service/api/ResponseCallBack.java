package com.codringreen.receptionloading.service.api;

public interface ResponseCallBack<T> {

    void onError(String message);

    void onSuccess(T data);
}