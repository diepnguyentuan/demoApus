package com.tiep.demoapus.dto.response;

public class ResponseWrapper {
    private Object data;

    public ResponseWrapper(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

