package com.fongwell.satchi.crm.core.common.error;

/**
 * Created by roman on 18-3-24.
 */
public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(String message) {
        super(message);
    }
}
