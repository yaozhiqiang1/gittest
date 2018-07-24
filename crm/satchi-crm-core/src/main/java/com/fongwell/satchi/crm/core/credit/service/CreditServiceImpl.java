package com.fongwell.satchi.crm.core.credit.service;

import com.fongwell.satchi.crm.core.credit.domain.aggregate.CreditConfiguration;
import com.fongwell.satchi.crm.core.credit.domain.aggregate.CustomerCredit;
import com.fongwell.satchi.crm.core.credit.domain.aggregate.CustomerCreditRecord;
import com.fongwell.satchi.crm.core.credit.domain.entity.CreditConsumeConfiguration;
import com.fongwell.satchi.crm.core.credit.domain.entity.CreditSourceConfiguration;
import com.fongwell.satchi.crm.core.credit.domain.value.ConsumeOption;
import com.fongwell.satchi.crm.core.credit.domain.value.CreditType;
import com.fongwell.satchi.crm.core.credit.query.mapper.*;
import com.fongwell.satchi.crm.core.credit.repository.CreditConfigurationRepository;
import com.fongwell.satchi.crm.core.credit.repository.CustomerCreditRecordRepository;
import com.fongwell.satchi.crm.core.credit.repository.CustomerCreditReposistory;
import com.fongwell.satchi.crm.core.customer.query.mapper.CustomerQueryMapper;
import com.fongwell.satchi.crm.core.product.dto.GifiCreditOrderDto;
import com.fongwell.satchi.crm.core.product.service.ProductServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by docker on 5/8/18.
 */
@Service("creditService")
public class CreditServiceImpl implements CreditService {

    @Resource(name = "creditConfigurationRepository")
    private CreditConfigurationRepository creditConfigurationRepository;

    @Resource(name = "customerCreditReposistory")
    private CustomerCreditReposistory customerCreditReposistory;

    @Resource(name = "customerCreditRecordRepository")
    private CustomerCreditRecordRepository customerCreditRecordRepository;

    @Resource
    private CreditConsumeConfigurationMapper creditConsumeConfigurationMapper;

    @Autowired
    private CreditQueryMapper creditQueryMapper;

    @Autowired
    private CreditUpdateMapper creditUpdateMapper;

    @Autowired
    private CreditSourceConfigurationMapper creditSourceConfigurationMapper;

    @Autowired
    private CustomerQueryMapper customerQueryMapper;

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

    /**
     * 保存用户积分
     */
    @Override
    public void creatCustomerCredit(CustomerCredit customerCredit) {

        customerCreditReposistory.save(customerCredit);

    }


    /**
     * 根据id查询客户积分类
     * @param customerId
     * @return
     */
    @Override
    public CustomerCredit queryById(Long customerId) {

        CustomerCredit customerCredit = customerCreditReposistory.findOne(customerId);

        return customerCredit;
    }

    /**
     * 更新积分
     * @param customerCredit
     */
    @Override
    public void updateCustomerCredit(CustomerCredit customerCredit) {
        customerCreditReposistory.saveAndFlush(customerCredit);

    }

    /**
     * 保存用户消费记录,并且更新客户消费的积分总数
     * @param customerCreditRecord
     * @param customerId
     */
    @Override
    public void saveCustomerConsumptionRecord(CustomerCreditRecord customerCreditRecord, long customerId) {



        Boolean creditConfigEnabled = creditQueryMapper.queryEnabled();
        if (creditConfigEnabled) {
            Long orderid = customerCreditRecord.getOrderid();
            BigDecimal price = customerCreditRecord.getPrice();


            Long creditConsumeConfigurationId = -1L;
            CreditConsumeConfiguration creditConsumeConfiguration = creditConsumeConfigurationMapper.queryCreditConsumeConfiguration(creditConsumeConfigurationId);


            if (creditConsumeConfiguration != null && creditConsumeConfiguration.getEnabled()) {
                //查询客户总积分
                Integer customerCreditTotal = creditQueryMapper.customerCreditTotal(customerId);
                if (customerCreditTotal != null && customerCreditTotal != 0) {

                    Integer consumptionCredits = getConsumptionCredits(creditConsumeConfiguration, customerCreditTotal, price);

                    creditUpdateMapper.creditUpdate(customerId, customerCreditTotal - consumptionCredits);

                    CustomerCreditRecord newCustomerCreditRecord =
                            new CustomerCreditRecord(customerId, orderid, price, consumptionCredits, CreditType.use, "PURCHASE");
                    newCustomerCreditRecord.setCustomerid(customerId);
                    customerCreditRecordRepository.save(newCustomerCreditRecord);

                }
            }
        }

    }

    /**
     * 保存用户获取积分记录
     * @param customerCreditRecord
     * @param customerId
     */
    @Override
    public Integer saveCustomerObtainCreditRecord(CustomerCreditRecord customerCreditRecord, long customerId) {
        Boolean creditConfigEnabled = creditQueryMapper.queryEnabled();
        if (creditConfigEnabled) {
            String type = "PURCHASE";
            CreditSourceConfiguration creditSourceConfiguration = creditSourceConfigurationMapper.queryByType(type);
            Boolean enabled = creditSourceConfiguration.getEnabled();
            if (enabled) {
                BigDecimal price = customerCreditRecord.getPrice();
                Long orderid = customerCreditRecord.getOrderid();
                Integer consumptionCredits = null;
                if (price != null && orderid != null) {
                    consumptionCredits = (int) (Double.valueOf(String.valueOf(price)) * creditSourceConfiguration.getCredit());

                    //查询客户总积分
                    Integer customerCreditTotal = creditQueryMapper.customerCreditTotal(customerId);
                    if (customerCreditTotal != null) {
                        creditUpdateMapper.creditUpdate(customerId, customerCreditTotal + consumptionCredits);

                        CustomerCreditRecord newCustomerCreditRecord =
                                new CustomerCreditRecord(customerId, orderid, price, consumptionCredits, CreditType.reward, "PURCHASE");
                        newCustomerCreditRecord.setCustomerid(customerId);
                        customerCreditRecordRepository.save(newCustomerCreditRecord);
                        return consumptionCredits;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 计算出用户消费多少积分可以换取多少金额
     * @param customerCreditRecord
     * @param customerId
     * @return
     */
    @Override
    public Map creditExchangeMoney(CustomerCreditRecord customerCreditRecord,long customerId ) {
        Boolean creditConfigEnabled = creditQueryMapper.queryEnabled();
        if (creditConfigEnabled) {
            Integer customerCreditTotal = creditQueryMapper.customerCreditTotal(customerId);
            BigDecimal price = customerCreditRecord.getPrice();
            Long creditConsumeConfigurationId = -1L;
            CreditConsumeConfiguration creditConsumeConfiguration = creditConsumeConfigurationMapper.queryCreditConsumeConfiguration(creditConsumeConfigurationId);
            BigDecimal cash = creditConsumeConfiguration.getCash();
            Integer consumptionCredits = getConsumptionCredits(creditConsumeConfiguration, customerCreditTotal, price);

            Map<String, Integer> map = new HashMap<String, Integer>();

            Integer creditExchangeMoney = (int) (Double.valueOf(String.valueOf(cash)) * consumptionCredits);
            map.put("creditExchangeMoney", creditExchangeMoney);
            map.put("consumptionCredits", consumptionCredits);
            return map;
        }
        return null;
    }

    /**
     * 客户签到是否超过一天
     * @return
     */
    @Override
    public boolean queryCheckindate(long customerId) {
        Date date = customerQueryMapper.queryCheckindate(customerId);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,1);
        Date time = calendar.getTime();
        Date newDate = new Date();

        return time.compareTo(newDate) < 0;

    }


    /**
     * 客户购买礼品时消费的积分
     * @param gifiCreditOrderDto
     * @return
     */
    @Override
    public Boolean purchaseGiftCredit(GifiCreditOrderDto gifiCreditOrderDto, long customerId) {
        Integer customerCreditTotal = creditQueryMapper.customerCreditTotal(customerId);
        if (customerCreditTotal < gifiCreditOrderDto.getCredit()){
            return false;
        }
        creditUpdateMapper.creditUpdate(customerId, customerCreditTotal - gifiCreditOrderDto.getCredit());
        CustomerCreditRecord newCustomerCreditRecord =
                new CustomerCreditRecord(customerId, gifiCreditOrderDto.getOrderid(), gifiCreditOrderDto.getPrice(), gifiCreditOrderDto.getCredit(), CreditType.use, "PURCHASE");
        newCustomerCreditRecord.setCustomerid(customerId);
        customerCreditRecordRepository.save(newCustomerCreditRecord);
        return true;
    }


    public Integer getConsumptionCredits(CreditConsumeConfiguration creditConsumeConfiguration,Integer customerCreditTotal,BigDecimal price){
        BigDecimal cash = creditConsumeConfiguration.getCash();
        Integer consumptionCredits = null;
        if (ConsumeOption.max_credit_per_order.equals(creditConsumeConfiguration.getOption())) {
            Integer maxCreditPerOrder = creditConsumeConfiguration.getMaxCreditPerOrder();
            if (customerCreditTotal > maxCreditPerOrder) {
                consumptionCredits = maxCreditPerOrder;

            } else {
                consumptionCredits = customerCreditTotal;
            }
        } else if (ConsumeOption.max_discount_percentage.equals(creditConsumeConfiguration.getOption())) {
            Integer maxDiscountPercentage = creditConsumeConfiguration.getMaxDiscountPercentage();
            Integer maxDiscountPercentageCredit = (int) (Double.valueOf(String.valueOf(price)) * maxDiscountPercentage / 100 / Double.valueOf(String.valueOf(cash)));
            if (customerCreditTotal > maxDiscountPercentageCredit) {
                consumptionCredits = maxDiscountPercentageCredit;
            } else {
                consumptionCredits = customerCreditTotal;
            }
        }
        return consumptionCredits;
    }

}
