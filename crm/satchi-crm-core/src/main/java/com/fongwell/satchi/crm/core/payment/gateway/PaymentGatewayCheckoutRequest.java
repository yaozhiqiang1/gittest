package com.fongwell.satchi.crm.core.payment.gateway;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by docker on 4/23/18.
 */
public class PaymentGatewayCheckoutRequest implements Serializable {

    @NotNull(message = "orderId.required")
    private Long orderId;


    private BigDecimal amount;
    @NotNull(message = "gatewayType.required")
    private String gatewayType;

    private Long customerId;

    private Map data;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(final Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public String getGatewayType() {
        return gatewayType;
    }

    public void setGatewayType(final String gatewayType) {
        this.gatewayType = gatewayType;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final Long customerId) {
        this.customerId = customerId;
    }

    public Map getData() {
        return data;
    }

    public void setData(final Map data) {
        this.data = data;
    }
}
