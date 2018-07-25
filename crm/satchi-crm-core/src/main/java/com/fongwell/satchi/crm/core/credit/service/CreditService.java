package com.fongwell.satchi.crm.core.credit.service;

import com.fongwell.satchi.crm.core.credit.domain.aggregate.CustomerCredit;
import com.fongwell.satchi.crm.core.credit.domain.aggregate.CustomerCreditRecord;
import com.fongwell.satchi.crm.core.product.dto.GifiCreditOrderDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * Created by docker on 5/8/18.
 */
@Transactional
public interface CreditService {

    Integer getCurrentlyAvailableCredits(long customerId, Date now);


    /**
     * 保存用户积分
     */
    void creatCustomerCredit(CustomerCredit customerCredit);

    /**
     * 根据id 查询用户积分类
     * @param customerId
     * @return
     */
    CustomerCredit queryById(Long customerId);

    /**
     * 签到更新积分
     * @param customerCredit
     */
    void updateCustomerCredit(CustomerCredit customerCredit);

    /**
     * 保存用户消费记录
     * @param customerCreditRecord
     * @param customerId
     */
    void saveCustomerConsumptionRecord(CustomerCreditRecord customerCreditRecord, long customerId);

    /**
     * 保存用户获取积分记录
     * @param customerCreditRecord
     * @param customerId
     */
    Integer saveCustomerObtainCreditRecord(CustomerCreditRecord customerCreditRecord, long customerId);

    /**
     * 计算出用户消费多少积分可以换取多少金额
     * @param customerCreditRecord
     * @param customerId
     * @return
     */
    Map creditExchangeMoney(CustomerCreditRecord customerCreditRecord, long customerId );

    /**
     * 客户签到是否超过一天
     * @return
     */
    boolean queryCheckindate(long customerId);

    /**
     * 客户购买礼品时消费的积分
     * @param gifiCreditOrderDto
     * @return
     */
    Boolean purchaseGiftCredit(GifiCreditOrderDto gifiCreditOrderDto, long customerId);
}
