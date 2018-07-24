package com.fongwell.satchi.crm.core.order.query.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by docker on 5/18/18.
 */
public class OrderStats implements Serializable {

    private BigDecimal expensePerOrder;

    private BigDecimal expensePerItem;

    private BigDecimal expenses;

    private int orders;

    private BigDecimal frequency;

    private BigDecimal discount;

    public void setDiscount(final BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public BigDecimal getFrequency() {
        return frequency;
    }

    public void setFrequency(final BigDecimal frequency) {
        this.frequency = frequency;
    }

    public BigDecimal getExpensePerOrder() {
        return expensePerOrder;
    }

    public void setExpensePerOrder(final BigDecimal expensePerOrder) {
        this.expensePerOrder = expensePerOrder;
    }

    public BigDecimal getExpensePerItem() {
        return expensePerItem;
    }

    public void setExpensePerItem(final BigDecimal expensePerItem) {
        this.expensePerItem = expensePerItem;
    }

    public BigDecimal getExpenses() {
        return expenses;
    }

    public void setExpenses(final BigDecimal expenses) {
        this.expenses = expenses;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(final int orders) {
        this.orders = orders;
    }
}
