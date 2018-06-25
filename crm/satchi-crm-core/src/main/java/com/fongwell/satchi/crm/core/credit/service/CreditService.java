package com.fongwell.satchi.crm.core.credit.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by docker on 5/8/18.
 */
@Transactional
public interface CreditService {

    Integer getCurrentlyAvailableCredits(long customerId, Date now);
}
