package com.fongwell.satchi.crm.core.pricing.service;

import com.fongwell.satchi.crm.core.pricing.ItemPrice;
import com.fongwell.satchi.crm.core.pricing.PricingContext;
import com.fongwell.satchi.crm.core.pricing.PricingResult;
import com.fongwell.satchi.crm.core.pricing.query.SkuPricingQueryRepository;
import com.fongwell.satchi.crm.core.pricing.query.dto.SkuPriceItem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by docker on 4/23/18.
 */
@Service("pricingService")
public class PricingServiceImpl implements PricingService {

    @Resource(name = "skuPricingQueryRepository")
    private SkuPricingQueryRepository skuPricingQueryRepository;


    @Override
    public PricingResult executePricing(final PricingContext context) {

        Map<Long, Integer> skus = context.getSkus();
        if (skus.isEmpty()) {
            return PricingResult.ZERO;
        }

        Collection<SkuPriceItem> priceItems = skuPricingQueryRepository.queryItems(skus.keySet());
        if (priceItems.isEmpty()) {
            return PricingResult.ZERO;
        }

        BigDecimal orderTotalPrice = BigDecimal.ZERO;
        BigDecimal orderTotalSalePrice = BigDecimal.ZERO;

        Collection<ItemPrice> itemPrices = new ArrayList<>(priceItems.size());

        for (final SkuPriceItem priceItem : priceItems) {
            Integer quantity = skus.get(priceItem.getId());

            ItemPrice price = new ItemPrice(priceItem.getId());

            price.setPrice(priceItem.getPrice());
            price.setSalePrice(priceItem.getSalePrice());
            price.setQuantity(quantity);

            BigDecimal quantityVal = new BigDecimal(quantity);

            price.setTotalPrice(price.getPrice() == null ? BigDecimal.ZERO : price.getPrice().multiply(quantityVal));
            price.setTotalSalePrice(price.getSalePrice() == null ? BigDecimal.ZERO : price.getSalePrice().multiply(quantityVal));
            itemPrices.add(price);
        }

        for (final ItemPrice itemPrice : itemPrices) {
            orderTotalPrice = orderTotalPrice.add(itemPrice.getTotalPrice());
            orderTotalSalePrice = orderTotalSalePrice.add(itemPrice.getTotalSalePrice());

        }

        PricingResult result = new PricingResult(orderTotalPrice, orderTotalSalePrice, itemPrices);
        return result;
    }
}
