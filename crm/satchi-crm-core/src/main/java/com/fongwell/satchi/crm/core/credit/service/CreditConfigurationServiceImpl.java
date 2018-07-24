package com.fongwell.satchi.crm.core.credit.service;

import com.fongwell.satchi.crm.core.credit.domain.aggregate.CreditConfiguration;
import com.fongwell.satchi.crm.core.credit.dto.CreditConfigurationDto;
import com.fongwell.satchi.crm.core.credit.dto.CreditConsumeConfigurationDto;
import com.fongwell.satchi.crm.core.credit.factory.CreditConfigurationFactory;
import com.fongwell.satchi.crm.core.credit.repository.CreditConfigurationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;

/**
 * Created by docker on 5/7/18.
 */
@Service("creditConfigurationService")
public class CreditConfigurationServiceImpl implements CreditConfigurationService {

    @Resource(name = "creditConfigurationRepository")
    private CreditConfigurationRepository creditConfigurationRepository;

    @Resource(name = "creditConfigurationFactory")
    private CreditConfigurationFactory creditConfigurationFactory;

    @Override
    @ResponseStatus(HttpStatus.OK)
    public void saveConfiguration(final CreditConfigurationDto data) {

        CreditConsumeConfigurationDto consumeConfiguration = data.getConsumeConfiguration();
        if (consumeConfiguration != null) {
            consumeConfiguration.setCredit(1);
        }

        CreditConfiguration config = creditConfigurationRepository.findOne(CreditConfiguration.DEFAULT_ID);

        if (config == null) {
            config = creditConfigurationFactory.create(CreditConfiguration.DEFAULT_ID, data);
        } else {
            creditConfigurationFactory.merge(config, data);
        }
        creditConfigurationRepository.save(config);
    }
}
