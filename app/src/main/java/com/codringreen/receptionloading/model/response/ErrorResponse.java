package com.codringreen.receptionloading.model.response;

import java.io.Serializable;

public class ErrorResponse implements Serializable {

    private Long errorcode;
    private String errormessage;

    public Long getErrorCode() {
        return this.errorcode;
    }

    public String getMessage() {
        return this.errormessage;
    }
}