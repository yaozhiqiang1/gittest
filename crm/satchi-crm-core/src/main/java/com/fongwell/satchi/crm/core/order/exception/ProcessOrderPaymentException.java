package com.fongwell.satchi.crm.core.order.exception;

/**
 * Created by docker on 4/24/18.
 */
public class ProcessOrderPaymentException extends RuntimeException {

    public ProcessOrderPaymentException(String message) {
        super(message);
    }

    public ProcessOrderPaymentException(String message, Throwable cause) {
        super(message, cause);
    }

}
