package com.fongwell.satchi.crm.core.credit.listener;

import com.fongwell.satchi.crm.core.credit.domain.aggregate.CustomerCredit;
import com.fongwell.satchi.crm.core.credit.domain.aggregate.CustomerCreditRecord;
import com.fongwell.satchi.crm.core.credit.domain.service.CreditCalculationService;
import com.fongwell.satchi.crm.core.credit.domain.value.CreditSource;
import com.fongwell.satchi.crm.core.credit.domain.value.CreditType;
import com.fongwell.satchi.crm.core.credit.repository.CustomerCreditRecordRepository;
import com.fongwell.satchi.crm.core.credit.repository.CustomerCreditReposistory;
import com.fongwell.satchi.crm.core.order.event.OrderPaidEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by docker on 5/22/18.
 */
@Component
public class CreditListenerImpl implements CreditListener {


    @Resource(name = "creditCalculationService")
    private CreditCalculationService creditCalculationService;

    @Resource(name = "customerCreditReposistory")
    private CustomerCreditReposistory customerCreditReposistory;

    @Resource(name = "customerCreditRecordRepository")
    private CustomerCreditRecordRepository customerCreditRecordRepository;


    @Override
    public void onOrderPaidEvent(final OrderPaidEvent event) {

        Integer credits = creditCalculationService.calculateCredits(CreditSource.PURCHASE, event.getCustomerId(), event.getSalePrice());

        if (credits != null && credits > 0) {
            CustomerCredit customerCredit = customerCreditReposistory.findOne(event.getCustomerId());
            if (customerCredit == null) {
                customerCredit = new CustomerCredit(event.getCustomerId(), credits);

            } else {
                customerCredit.increment(credits);
            }

            customerCreditReposistory.save(customerCredit);

            customerCreditRecordRepository.save(new CustomerCreditRecord(event.getCustomerId(), event.getOrderId(), event.getSalePrice(), credits, CreditType.reward, CreditSource.PURCHASE.getType()));


        }
    }

    @Override
    public Class<?> getTargetClass() {
        return CreditListenerImpl.class;
    }
}
