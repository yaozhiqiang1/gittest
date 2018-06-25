package com.fongwell.satchi.crm.core.order.exception;

/**
 * Created by docker on 4/24/18.
 */
public abstract class CheckoutException extends RuntimeException {

    public CheckoutException(String message) {
        super(message);
    }
}
