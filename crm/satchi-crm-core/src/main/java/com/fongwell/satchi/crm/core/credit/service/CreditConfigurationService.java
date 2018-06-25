package com.fongwell.satchi.crm.core.credit.service;

import com.fongwell.satchi.crm.core.credit.dto.CreditConfigurationDto;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by docker on 5/7/18.
 */
@Transactional
public interface CreditConfigurationService {

    /**
     * 保存积分设置
     * @param data 积分配置实体类
     */
    void saveConfiguration(CreditConfigurationDto data);
}
