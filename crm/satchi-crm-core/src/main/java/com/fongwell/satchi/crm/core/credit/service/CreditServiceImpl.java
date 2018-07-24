package com.fongwell.satchi.crm.core.credit.service;

import com.fongwell.satchi.crm.core.credit.domain.aggregate.CreditConfiguration;
import com.fongwell.satchi.crm.core.credit.repository.CreditConfigurationRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by docker on 5/8/18.
 */
@Service("creditService")
public class CreditServiceImpl implements CreditService {

    @Resource(name = "creditConfigurationRepository")
    private CreditConfigurationRepository creditConfigurationRepository;

    @Override
    public Integer getCurrentlyAvailableCredits(final long customerId, final Date now) {

        CreditConfiguration config = creditConfigurationRepository.findOne(CreditConfiguration.DEFAULT_ID);
        if (config == null || config.getEnabled() == null || !config.getEnabled()) {
            return null;
        }

        if (!config.isApplicable(now)) {
            return null;
        }




        return null;
    }
}
