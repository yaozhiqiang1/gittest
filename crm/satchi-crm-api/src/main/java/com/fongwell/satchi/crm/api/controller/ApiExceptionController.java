package com.fongwell.satchi.crm.api.controller;

import com.fongwell.base.rest.Payload;
import com.fongwell.base.validate.ValidationException;
import com.fongwell.satchi.crm.core.common.error.DataNotFoundException;
import com.fongwell.satchi.crm.core.customer.exception.DuplicateCustomerMobileException;
import com.fongwell.satchi.crm.core.order.exception.InvalidSkusException;
import com.fongwell.satchi.crm.core.product.service.inventory.exception.InsufficientInventoryException;
import com.fongwell.satchi.crm.wx.user.binding.DuplicateBindTargetException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by docker on 2/25/18.
 */
@RestControllerAdvice
public class ApiExceptionController {


    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(DuplicateCustomerMobileException.class)
    public Payload onDuplicateCustomerMobileException(DuplicateCustomerMobileException e) {

        Payload payload = new Payload();
        payload.setCode(1001);
        payload.setMsg(e.getMessage());
        return payload;

    }


    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(ValidationException.class)
    public Payload onValidationException(ValidationException e, HttpServletRequest request) {

        final WebApplicationContext appContext = RequestContextUtils.findWebApplicationContext(request,
                request.getServletContext());

        BindingResult result = e.getResult();

        List<FieldError> errors = result.getFieldErrors();

        Map map = new HashMap(errors.size(), 2f);
        for (FieldError error : errors) {
            map.put(error.getField(),
                    appContext.getMessage(error.getDefaultMessage(), null, error.getDefaultMessage(),
                            request.getLocale()));
        }

        Payload payload = new Payload(map);
        payload.setCode(400);
        return payload;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(InsufficientInventoryException.class)
    public Payload onInsufficientInventoryException(InsufficientInventoryException e) {

        Payload payload = new Payload();
        payload.setCode(2001);
        payload.setMsg(e.getMessage());
        payload.setData(e.getSkuIds());
        return payload;

    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(InvalidSkusException.class)
    public Payload onInvalidSkusException(InvalidSkusException e) {

        Payload payload = new Payload();
        payload.setCode(2002);
        payload.setMsg(e.getMessage());
        payload.setData(e.getMissingSkuIds());
        return payload;

    }


    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(DataNotFoundException.class)
    public Payload onDataNotFoundException(DataNotFoundException e) {

        Payload payload = new Payload();
        payload.setCode(2003);
        payload.setMsg(e.getMessage());

        return payload;

    }


    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(DuplicateBindTargetException.class)
    public Payload onDuplicateBindTargetException(DuplicateBindTargetException e) {

        Payload payload = new Payload();
        payload.setCode(2004);
        payload.setMsg(e.getMessage());

        return payload;

    }


}
