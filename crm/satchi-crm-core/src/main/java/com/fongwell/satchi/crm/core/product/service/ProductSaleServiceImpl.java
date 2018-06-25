package com.fongwell.satchi.crm.core.product.service;

import com.fongwell.satchi.crm.core.order.event.OrderEventItem;
import com.fongwell.satchi.crm.core.order.event.OrderPaidEvent;
import com.fongwell.satchi.crm.core.product.domain.aggregate.Product;
import com.fongwell.satchi.crm.core.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by docker on 5/11/18.
 */
@Service("productSaleService")
public class ProductSaleServiceImpl implements ProductSaleService {


    @Autowired
    private ProductRepository productRepository;

    @Override
    public void onOrderPaidEvent(final OrderPaidEvent event) {

        Collection<OrderEventItem> items = event.getItems();
        Map<Long, Integer> quantities = new HashMap<>(items.size(), 2f);

        for (final OrderEventItem item : items) {
            Integer quantity = quantities.get(item.getProductId());
            if (quantity == null) {
                quantity = item.getQuantity();

            } else {
                quantity = quantity + item.getQuantity();
            }
            quantities.put(item.getProductId(), quantity);
        }

        for (final Map.Entry<Long, Integer> entry : quantities.entrySet()) {
            Product product = productRepository.findOne(entry.getKey());
            if (product != null) {
                Integer sale = product.getSale();
                if (sale == null) {
                    sale = entry.getValue();
                } else {
                    sale = sale + entry.getValue();
                }
                product.setSale(sale);
                productRepository.save(product);

            }

        }
    }

    @Override
    public Class<?> getTargetClass() {
        return ProductSaleServiceImpl.class;
    }
}
