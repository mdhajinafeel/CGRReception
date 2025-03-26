package com.codringreen.receptionloading.model.response;

import java.io.Serializable;

public class ContainerNumberMasterResponse implements Serializable {

    private String containerNumber;

    public String getContainerNumber() {
        return this.containerNumber;
    }

    public void setContainerNumber(String containerNumber) {
        this.containerNumber = containerNumber;
    }
}