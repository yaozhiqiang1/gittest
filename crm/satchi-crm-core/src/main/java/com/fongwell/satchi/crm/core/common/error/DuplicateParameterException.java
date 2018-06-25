package com.fongwell.satchi.crm.core.common.error;

/**
 * Created by roman on 18-4-3.
 */
public class DuplicateParameterException extends RuntimeException {

    public DuplicateParameterException(String message) {
        super(message);
    }

    public DuplicateParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}
