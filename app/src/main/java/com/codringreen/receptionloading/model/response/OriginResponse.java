package com.codringreen.receptionloading.model.response;

import java.io.Serializable;
import java.util.List;

public class OriginResponse implements Serializable {

    private List<OriginDataResponse> data;
    private String message;
    private boolean status;

    public List<OriginDataResponse> getData() {
        return data;
    }

    public void setData(List<OriginDataResponse> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}