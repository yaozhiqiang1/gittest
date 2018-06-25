package com.fongwell.satchi.crm.api;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * created by roman on 17-7-6.
 */
@ControllerAdvice
public class JsonpAdviceController extends AbstractJsonpResponseBodyAdvice {

    public JsonpAdviceController() {
        super("callback");
    }
}
