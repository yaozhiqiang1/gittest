package com.fongwell.satchi.crm.core.credit.factory;

import com.fongwell.satchi.crm.core.credit.domain.aggregate.CreditConfiguration;
import com.fongwell.satchi.crm.core.credit.domain.entity.CreditConsumeConfiguration;
import com.fongwell.satchi.crm.core.credit.domain.entity.CreditSourceConfiguration;
import com.fongwell.satchi.crm.core.credit.dto.CreditConfigurationDto;
import com.fongwell.satchi.crm.core.credit.dto.CreditConsumeConfigurationDto;
import com.fongwell.satchi.crm.core.credit.dto.CreditSourceConfigurationDto;
import com.fongwell.satchi.crm.core.credit.query.mapper.CreditSourceConfigurationMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.*;

/**
 * Created by docker on 5/7/18.
 */
@Component("creditConfigurationFactory")
public class CreditConfigurationFactoryImpl implements CreditConfigurationFactory, InitializingBean {

    private final Map<String, CreditSourceConfigurationFactory> sourceFactoriesMap = new HashMap<>();

    @Autowired
    private Map<String, CreditSourceConfigurationFactory> sourceFactories;

    @Autowired
    private CreditSourceConfigurationMapper creditSourceConfigurationMapper;

    /** 大神
     * 积分设置
     * 数据封装到实体类CreditConfiguration
     * @param id
     * @param data 自定义实体类CreditConfigurationDto信息
     * @return  CreditConfiguration 实体类
     */
 /*   @Override
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



               //数据封装到实体类 CreditSourceConfiguration
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
*/


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
                CreditSourceConfiguration creditSourceConfiguration = new CreditSourceConfiguration();
                Long creditSourceConfigurationId = Long.valueOf(RandomStringUtils.randomNumeric(16));
                creditSourceConfiguration.setId(creditSourceConfigurationId);
                creditSourceConfiguration.setCredit(sourceItem.getCredit());
                creditSourceConfiguration.setEnabled(sourceItem.getEnabled());
                creditSourceConfiguration.setParentId(id);
                creditSourceConfiguration.setType(sourceItem.getType());
                creditSourceConfigurationMapper.saveCreditSourceConfiguration(creditSourceConfiguration);

                String type = sourceItem.getType();

                CreditSourceConfigurationFactory sourceFactory = sourceFactoriesMap.get(type);
                Assert.notNull(sourceFactory, "CreditSourceConfigurationFactory for type:" + type);

                //数据封装到实体类 CreditSourceConfiguration
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

                CreditSourceConfiguration creditSourceConfiguration = new CreditSourceConfiguration();
                creditSourceConfiguration.setCredit(sourceItem.getCredit());
                creditSourceConfiguration.setEnabled(sourceItem.getEnabled());
                creditSourceConfiguration.setType(sourceItem.getType());
                creditSourceConfigurationMapper.updateCreditSourceConfiguration(creditSourceConfiguration);

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
