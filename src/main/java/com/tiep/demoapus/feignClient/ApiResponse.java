package com.tiep.demoapus.feignClient;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private String message;
    private String traceId;
    private T data;
}
