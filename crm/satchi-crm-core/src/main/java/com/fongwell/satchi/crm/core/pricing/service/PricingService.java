package com.fongwell.satchi.crm.core.pricing.service;

import com.fongwell.satchi.crm.core.pricing.PricingResult;
import com.fongwell.satchi.crm.core.pricing.PricingContext;

/**
 * Created by docker on 4/23/18.
 */
public interface PricingService {

    PricingResult executePricing(PricingContext context);
}
