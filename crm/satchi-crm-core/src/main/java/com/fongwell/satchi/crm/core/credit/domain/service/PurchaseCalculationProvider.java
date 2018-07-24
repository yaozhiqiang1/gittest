package com.fongwell.satchi.crm.core.credit.domain.service;

import com.fongwell.satchi.crm.core.credit.domain.entity.PurchaseConfiguration;
import com.fongwell.satchi.crm.core.credit.domain.value.CreditSource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by docker on 5/22/18.
 */
@Component
public class PurchaseCalculationProvider implements CreditSourceCalculationProvider {
    @Override
    public Integer calculateCredits(final CreditSourceCalculationContext context) {

        PurchaseConfiguration config = (PurchaseConfiguration) context.getConfiguration();

        Integer credit = config.getCredit();
        if (credit == null) {
            return null;
        }

        BigDecimal orderTotal = (BigDecimal) context.getData();

        if (orderTotal == null) {
            return null;
        }

        return 1;
    }

    @Override
    public String getType() {
        return CreditSource.PURCHASE.getType();
    }
}
