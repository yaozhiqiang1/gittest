package com.fongwell.satchi.crm.api.jobs;

import com.fongwell.satchi.crm.core.credit.service.CreditService;
import com.fongwell.satchi.crm.core.product.service.ProductService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Date:2018/7/16
 * Author: yaozhiqiang
 * Desc:
 */
public class PromotionJob implements Job {

    @Resource
    private ProductService productService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //更新状态
        productService.updateProductStatus(new Date());
    }
}
