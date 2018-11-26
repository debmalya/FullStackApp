package com.rakuten.fullstackrecruitmenttest.exception;

public class UploadException extends RuntimeException {
    public UploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public UploadException(String message) {
        super(message);
    }
}
