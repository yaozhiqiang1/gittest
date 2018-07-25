package com.fongwell.satchi.crm.core.credit.domain.service;

import com.fongwell.satchi.crm.core.credit.domain.aggregate.CreditConfiguration;
import com.fongwell.satchi.crm.core.credit.domain.entity.CreditSourceConfiguration;
import com.fongwell.satchi.crm.core.credit.domain.value.CreditSource;
import com.fongwell.satchi.crm.core.credit.repository.CreditConfigurationRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by docker on 5/22/18.
 */
@Service("creditCalculationService")
public class CreditCalculationServiceImpl implements CreditCalculationService, InitializingBean {

    @Autowired
    private CreditConfigurationRepository creditConfigurationRepository;

    @Autowired
    private Map<String, CreditSourceCalculationProvider> creditSourceCalculationProviders;

    private Map<String, CreditSourceCalculationProvider> creditSourceCalculationProvidersTypeMap;


    @Override
    public Integer calculateCredits(CreditSource source, final long customerId, Object data) {

        CreditConfiguration config = creditConfigurationRepository.findOne(CreditConfiguration.DEFAULT_ID);
        if (config == null) {
            return null;
        }
        if (config.getEnabled() == null || !config.getEnabled()) {
            return null;
        }

        CreditSourceConfiguration sourceConfiguration = config.getSource(source.getType());
        
        System.out.println("");
        System.out.println("source.getType():"+source.getType());
        System.out.println("sourceConfiguration:"+sourceConfiguration.toString());
        System.out.println("");
        
        Assert.notNull(sourceConfiguration, "CreditSourceConfiguration: " + source.getType());

        CreditSourceCalculationProvider provider = getProvider(source);
        return provider.calculateCredits(new CreditSourceCalculationContext(sourceConfiguration, customerId, data));
    }

    private CreditSourceCalculationProvider getProvider(CreditSource type) {
        CreditSourceCalculationProvider provider = creditSourceCalculationProvidersTypeMap.get(type.getType());
        Assert.notNull(provider, "CreditSourceCalculationProvider:" + type);
        return provider;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        Assert.notEmpty(creditSourceCalculationProviders, "creditSourceCalculationProviders");

        creditSourceCalculationProvidersTypeMap = new HashMap<>();
        for (final CreditSourceCalculationProvider provider : creditSourceCalculationProviders.values()) {
            creditSourceCalculationProvidersTypeMap.put(provider.getType(), provider);
        }


    }
}
