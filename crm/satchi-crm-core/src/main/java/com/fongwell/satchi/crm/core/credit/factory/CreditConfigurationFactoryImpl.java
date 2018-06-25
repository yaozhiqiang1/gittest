package com.fongwell.satchi.crm.core.credit.factory;

import com.fongwell.satchi.crm.core.credit.domain.aggregate.CreditConfiguration;
import com.fongwell.satchi.crm.core.credit.domain.entity.CreditConsumeConfiguration;
import com.fongwell.satchi.crm.core.credit.domain.entity.CreditSourceConfiguration;
import com.fongwell.satchi.crm.core.credit.dto.CreditConfigurationDto;
import com.fongwell.satchi.crm.core.credit.dto.CreditConsumeConfigurationDto;
import com.fongwell.satchi.crm.core.credit.dto.CreditSourceConfigurationDto;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by docker on 5/7/18.
 */
@Component("creditConfigurationFactory")
public class CreditConfigurationFactoryImpl implements CreditConfigurationFactory, InitializingBean {

    private final Map<String, CreditSourceConfigurationFactory> sourceFactoriesMap = new HashMap<>();

    @Autowired
    private Map<String, CreditSourceConfigurationFactory> sourceFactories;


    /**
     * 积分设置
     * 数据封装到实体类CreditConfiguration
     * @param id
     * @param data 自定义实体类CreditConfigurationDto信息
     * @return  CreditConfiguration 实体类
     */
    @Override
    public CreditConfiguration create(long id, final CreditConfigurationDto data) {
        CreditConfiguration configuration = new CreditConfiguration(id);

        configuration.setEnabled(data.getEnabled());
        configuration.setExpiration(data.getExpiration());

        Collection<CreditSourceConfigurationDto> sourcesData = data.getSources();
        if (sourcesData != null) {
            for (final CreditSourceConfigurationDto sourceItem : sourcesData) {


                String type = sourceItem.getType();

                CreditSourceConfigurationFactory sourceFactory = sourceFactoriesMap.get(type);
                Assert.notNull(sourceFactory, "CreditSourceConfigurationFactory for type:" + type);

               //数据封装到实体类CreditSourceConfiguration
                CreditSourceConfiguration source = sourceFactory.create(id, sourceItem);
                configuration.getSources().add(source);
            }
        }

        CreditConsumeConfigurationDto consumeConfigurationData = data.getConsumeConfiguration();
        if (consumeConfigurationData != null) {

            CreditConsumeConfiguration consumeConfiguration = new CreditConsumeConfiguration(id, consumeConfigurationData);
            configuration.setConsumeConfiguration(consumeConfiguration);
        }


        return configuration;
    }

    @Override
    public void merge(final CreditConfiguration configuration, final CreditConfigurationDto data) {
        configuration.setEnabled(data.getEnabled());
        configuration.setExpiration(data.getExpiration());

        Collection<CreditSourceConfigurationDto> sourcesData = data.getSources();

        Collection<CreditSourceConfiguration> newSources = new LinkedList<>();
        if (sourcesData != null) {
            for (final CreditSourceConfigurationDto sourceItem : sourcesData) {


                String type = sourceItem.getType();
                CreditSourceConfigurationFactory sourceFactory = sourceFactoriesMap.get(type);
                Assert.notNull(sourceFactory, "CreditSourceConfigurationFactory for type:" + type);

                CreditSourceConfiguration source = configuration.getSource(type);
                if (source == null) {
                    //参数封装到CreditSourceConfiguration 实体类
                    source = sourceFactory.create(configuration.getId(), sourceItem);
                    configuration.addSource(source);
                } else {
                    sourceFactory.merge(source, sourceItem);
                }

            }

        }


        configuration.setSources(newSources);

        CreditConsumeConfigurationDto consumeConfigurationData = data.getConsumeConfiguration();
        if (consumeConfigurationData == null) {
            configuration.setConsumeConfiguration(null);
        } else {
            CreditConsumeConfiguration consumeConfiguration = configuration.getConsumeConfiguration();
            if (consumeConfiguration == null) {
                consumeConfiguration = new CreditConsumeConfiguration(configuration.getId(), consumeConfigurationData);
                configuration.setConsumeConfiguration(consumeConfiguration);
            } else {
                consumeConfiguration.updateProperties(consumeConfigurationData);
            }

        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {

        Assert.notEmpty(sourceFactories);
        for (final CreditSourceConfigurationFactory factory : sourceFactories.values()) {
            sourceFactoriesMap.put(factory.getType(), factory);

        }

    }
}
