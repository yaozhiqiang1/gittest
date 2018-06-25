package com.fongwell.satchi.crm.core.product.service;

import com.fongwell.infrastructure.event.publish.EventPublisher;
import com.fongwell.satchi.crm.core.common.State;
import com.fongwell.satchi.crm.core.common.error.DataNotFoundException;
import com.fongwell.satchi.crm.core.common.error.DuplicateParameterException;
import com.fongwell.satchi.crm.core.product.domain.aggregate.Product;
import com.fongwell.satchi.crm.core.product.domain.aggregate.entity.ProductSettings;
import com.fongwell.satchi.crm.core.product.domain.aggregate.entity.Sku;
import com.fongwell.satchi.crm.core.product.dto.ProductData;
import com.fongwell.satchi.crm.core.product.repository.ProductRepository;
import com.fongwell.satchi.crm.core.product.service.event.ProductDisableEvent;
import com.fongwell.satchi.crm.core.product.service.event.ProductEnableEvent;
import com.fongwell.satchi.crm.core.support.ddd.AggregateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by roman on 18-4-3.
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private AggregateFactory<Product, ProductData> aggregateFactory;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EventPublisher eventPublisher;

    @Override
    public Long onCreate(ProductData data) {
        Product numberProduct = productRepository.findByNumber(data.getNumber());
        if (numberProduct != null) {
            throw new DuplicateParameterException("product.number duplicate :" + data.getNumber());
        }
        Product product = aggregateFactory.createAggregate(data, Product.class);

        product.fixImages();
        ProductSettings settings = product.onSettings();
        product.setItems(Collections.singletonList(new Sku(product.getNumber(), settings.getPrice(), settings.getOriginalPrice(), settings.getCredit(), product.getId(), data.getInventory())));
        productRepository.save(product);
        return product.getId();
    }

    @Override
    public void onUpdate(long id, ProductData data) {
        Product one = productRepository.findOne(id);
        if (one == null) {
            throw new DataNotFoundException("product not found :" + id);
        }

        Product numberProduct = productRepository.findByNumber(data.getNumber());
        if (numberProduct != null && id != numberProduct.getId()) {
            throw new DuplicateParameterException("product.number duplicate :" + data.getNumber());
        }


        aggregateFactory.mergeAggregate(data, one);

        one.fixImages();
        ProductSettings settings = one.getSettings();

        Collection<Sku> skus = one.getItems();
        if (skus == null || skus.isEmpty()) {
            one.setItems(Collections.singletonList(new Sku(one.getNumber(), settings.getPrice(), settings.getOriginalPrice(), settings.getCredit(), one.getId(), data.getInventory())));
        } else {
            Sku sku = skus.iterator().next();
            sku.update(one.getNumber(), settings.getPrice(), settings.getOriginalPrice(), settings.getCredit(), data.getInventory());
        }

        productRepository.save(one);
    }

    @Override
    public void onDisable(Collection<Long> ids) {
        List<Product> all = productRepository.findAll(ids);
        Collection<ProductData> targets = new ArrayList<>(all.size());
        for (Product next : all) {
            if (State.enable.equals(next.getState())) {
                next.onDisable();
                productRepository.save(next);
                targets.add(new ProductData(next.getNumber()));
            }
        }

        if (!CollectionUtils.isEmpty(targets)) {
            eventPublisher.publish(new ProductDisableEvent(targets));
        }
    }

    @Override
    public void onEnable(Collection<Long> ids) {
        List<Product> all = productRepository.findAll(ids);
        Collection<ProductData> targets = new ArrayList<>(all.size());
        for (Product next : all) {
            if (State.disable.equals(next.getState())) {
                next.onEnable();
                productRepository.save(next);
                targets.add(new ProductData(next.getNumber()));
            }
        }

        if (!CollectionUtils.isEmpty(targets)) {
            eventPublisher.publish(new ProductEnableEvent(targets));
        }
    }

    @Override
    public void onDelete(Collection<Long> ids) {
        List<Product> all = productRepository.findAll(ids);
        for (Product next : all) {
            if (!next.isDelete()) {
                next.onDelete();
                productRepository.save(next);
            }
        }
    }

    @Override
    public void onSortting(Long id, int orderNumber) {
        Product one = productRepository.findOne(id);
        if (one == null) {
            throw new DataNotFoundException("product not found :" + id);
        }
        one.setOrderNumber(orderNumber);
        productRepository.save(one);
    }
}
