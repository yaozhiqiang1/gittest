package com.fongwell.satchi.crm.core.order.domain.service.acitivity;

import com.fongwell.base.workflow.BaseActivity;
import com.fongwell.base.workflow.WorkflowContext;
import com.fongwell.base.workflow.WorkflowException;
import com.fongwell.satchi.crm.core.order.domain.aggregate.Order;
import com.fongwell.satchi.crm.core.order.domain.service.ProcessOrderPaymentData;
import com.fongwell.satchi.crm.core.order.domain.value.OrderState;
import com.fongwell.satchi.crm.core.order.exception.ProcessOrderPaymentException;
import org.springframework.stereotype.Component;

/**
 * Created by docker on 4/24/18.
 */
@Component
public class ValidateOrderStateActivity<T extends WorkflowContext<ProcessOrderPaymentData>> extends BaseActivity<T> {


    @Override
    public void execute(final T context) throws WorkflowException {

        Order order = context.getData().getOrder();

        if (order.getState() != OrderState.pending) {
           // throw new ProcessOrderPaymentException("Invalid order state: " + order.getState());
        }

    }

    @Override
    public boolean shouldExecute(final T context) {
        return true;
    }
}
