package com.fongwell.satchi.crm.api.jobs;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Date:2018/7/16
 * Author: yaozhiqiang
 * Desc:
 */
@Component
public class MyJobFactory extends AdaptableJobFactory {

    @Resource
    private AutowireCapableBeanFactory factory;


    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {

        Object jobInstance = super.createJobInstance(bundle);
        factory.autowireBean(jobInstance);
        return jobInstance;
    }


}
