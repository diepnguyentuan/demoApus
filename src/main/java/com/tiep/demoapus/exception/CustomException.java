package com.tiep.demoapus.exception;

import org.springframework.http.HttpStatus;

/**
 * CustomException dùng để ném ngoại lệ với thông báo lỗi và trạng thái HTTP tương ứng.
 */
public class CustomException extends RuntimeException {
    private final HttpStatus status;

    public CustomException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
