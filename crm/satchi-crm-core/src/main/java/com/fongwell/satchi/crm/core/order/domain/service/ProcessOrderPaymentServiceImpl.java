package com.fongwell.satchi.crm.core.order.domain.service;

import com.fongwell.base.workflow.Workflow;
import com.fongwell.satchi.crm.core.order.domain.aggregate.Order;
import com.fongwell.satchi.crm.core.payment.gateway.dto.PaymentGatewayResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by docker on 4/24/18.
 */
@Service("processOrderPaymentService")
public class ProcessOrderPaymentServiceImpl implements ProcessOrderPaymentService {


    @Resource(name = "processOrderPaymentWorkflow")
    private Workflow processOrderPaymentWorkflow;

    @Override
    public void processOrderPayment(final Order order, final PaymentGatewayResponse payment) {

        processOrderPaymentWorkflow.execute(new ProcessOrderPaymentData(order, payment));


    }
}
