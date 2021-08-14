package com.hackthon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Resource not found exception.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final int errorCode;
    private final String errorMessage;


    /**
     * Gets error code.
     *
     * @return the error code
     */
    public int getErrorCode() {
        return errorCode;
    }


    /**
     * Gets error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Instantiates a new Resource not found exception.
     *
     * @param errorCode    the error code
     * @param errorMessage the error message
     */
    public ResourceNotFoundException(int errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}