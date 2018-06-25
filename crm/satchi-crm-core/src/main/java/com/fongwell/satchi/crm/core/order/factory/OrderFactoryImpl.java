package com.fongwell.satchi.crm.core.order.factory;

import com.fongwell.satchi.crm.core.order.checkout.CheckoutContext;
import com.fongwell.satchi.crm.core.order.domain.aggregate.Order;
import com.fongwell.satchi.crm.core.order.domain.entity.OrderItem;
import com.fongwell.satchi.crm.core.pricing.ItemPrice;
import com.fongwell.satchi.crm.core.pricing.PricingResult;
import com.fongwell.satchi.crm.core.product.dto.SkuInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by docker on 4/23/18.
 */
@Service("orderFactory")
public class OrderFactoryImpl implements OrderFactory {


    @Override
    public Order createOrderFromCheckout(final CheckoutContext context, PricingResult priceInfo) {
        Collection<OrderItem> items = new ArrayList<>(context.getSkus().size());

        Collection<ItemPrice> itemPrices = priceInfo.getItemPrices();
        Map<Long, ItemPrice> itemPricesMap = new HashMap<>((int) (itemPrices.size() / 0.75) + 1);

        for (final ItemPrice itemPrice : itemPrices) {
            itemPricesMap.put(itemPrice.getId(), itemPrice);
        }

        Order order = new Order(context.getCustomerId(), items, priceInfo.getPrice(), priceInfo.getSalePrice(), context.getRequest().getPaymentGatewayType());

        for (final SkuInfo sku : context.getSkus().keySet()) {


            ItemPrice itemPrice = itemPricesMap.get(sku.getId());


            if (itemPrice == null) {

                throw new IllegalArgumentException("No ItemPrice for sku: " + sku.getId());
            } else {
                OrderItem item = new OrderItem(order.getId(), sku.getProductId(), sku.getId(), itemPrice.getQuantity());

                item.setMobileImage(sku.getMobileImage());
                item.setPcImage(sku.getPcImage());
                item.setSalePrice(itemPrice.getSalePrice());
                item.setTotalSalePrice(itemPrice.getTotalSalePrice());
                item.setPrice(itemPrice.getPrice());
                item.setTotalPrice(itemPrice.getTotalPrice());
                item.setName(sku.getName());
                item.setSkuNumber(sku.getSkuNumber());
                items.add(item);
            }

        }

        order.setShippingAddress(context.getAddress());

        return order;
    }
}
