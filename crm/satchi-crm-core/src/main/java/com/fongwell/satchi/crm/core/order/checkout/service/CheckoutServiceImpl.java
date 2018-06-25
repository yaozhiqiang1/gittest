package com.fongwell.satchi.crm.core.order.checkout.service;

import com.fongwell.infrastructure.event.publish.EventPublisher;
import com.fongwell.satchi.crm.core.customer.domain.value.AddressValue;
import com.fongwell.satchi.crm.core.customer.query.repository.CustomerAddressQueryRepository;
import com.fongwell.satchi.crm.core.order.checkout.CheckoutContext;
import com.fongwell.satchi.crm.core.order.domain.aggregate.Order;
import com.fongwell.satchi.crm.core.order.domain.service.ProcessOrderPaymentService;
import com.fongwell.satchi.crm.core.order.dto.*;
import com.fongwell.satchi.crm.core.order.event.OrderPaidEvent;
import com.fongwell.satchi.crm.core.order.exception.InvalidSkusException;
import com.fongwell.satchi.crm.core.order.factory.OrderFactory;
import com.fongwell.satchi.crm.core.order.repository.OrderRepository;
import com.fongwell.satchi.crm.core.order.utils.OrderUtils;
import com.fongwell.satchi.crm.core.payment.gateway.dto.PaymentGatewayResponse;
import com.fongwell.satchi.crm.core.pricing.ItemPrice;
import com.fongwell.satchi.crm.core.pricing.PricingContext;
import com.fongwell.satchi.crm.core.pricing.PricingResult;
import com.fongwell.satchi.crm.core.pricing.service.PricingService;
import com.fongwell.satchi.crm.core.product.dto.SkuInfo;
import com.fongwell.satchi.crm.core.product.query.ProductInfoQueryRepository;
import com.fongwell.satchi.crm.core.product.service.inventory.InventoryService;
import com.fongwell.satchi.crm.core.product.service.inventory.exception.InsufficientInventoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by docker on 4/19/18.
 */
@Service("checkoutService")
public class CheckoutServiceImpl implements CheckoutService {

    private static final Logger LOGGER = LogManager.getLogger(CheckoutServiceImpl.class);

    @Resource(name = "customerAddressQueryRepository")
    private CustomerAddressQueryRepository customerAddressQueryRepository;

    @Resource(name = "processOrderPaymentService")
    private ProcessOrderPaymentService processOrderPaymentService;

    @Resource(name = "inventoryService")
    private InventoryService inventoryService;

    @Resource(name = "pricingService")
    private PricingService pricingService;

    @Resource(name = "orderFactory")
    private OrderFactory orderFactory;

    @Resource(name = "productInfoQueryRepository")
    private ProductInfoQueryRepository productInfoQueryRepository;

    @Resource(name = "orderRepository")
    private OrderRepository orderRepository;

    @Resource(name = "eventPublisher")
    private EventPublisher eventPublisher;

    @Override
    public Order checkout(long customerId, final CheckoutRequest request) {

        AddressValue address = customerAddressQueryRepository.queryAddress(request.getAddressId());

        Collection<CheckoutItem> items = request.getItems();
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("items to checkout are null or empty!");
        }

        Collection<Long> skuIds = new ArrayList<>(items.size());
        Map<Long, Integer> requestedQuantity = new HashMap<>((int) (items.size() / 0.75f + 1));
        for (final CheckoutItem item : items) {
            skuIds.add(item.getSkuId());
            requestedQuantity.put(item.getSkuId(), item.getQuantity());
        }

        validateInventory(skuIds, requestedQuantity);

        PricingResult pricingResult = executePricing(customerId, requestedQuantity);

        Collection<SkuInfo> skuInfos = productInfoQueryRepository.queryActiveSkuInfos(skuIds);

        validateSkus(skuInfos, requestedQuantity);


        Map<SkuInfo, Integer> skus = new HashMap<>((int) (skuInfos.size() / 0.75f + 1));
        for (final SkuInfo skuInfo : skuInfos) {
            Integer quantity = requestedQuantity.get(skuInfo.getId());
            skus.put(skuInfo, quantity);
        }

        Order order = orderFactory.createOrderFromCheckout(new CheckoutContext(customerId, request, skus, address), pricingResult);

        orderRepository.save(order);

        inventoryService.decrementInventoryForCheckout(order.getId(), order.getCustomerId(), requestedQuantity);


        return order;
    }

    @Override
    public UpdateCartResult updateCart(final long customerId, final Collection<CheckoutItem> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("items  are null or empty!");
        }


        Collection<Long> skuIds = new ArrayList<>(items.size());
        Map<Long, Integer> requestedQuantity = new HashMap<>((int) (items.size() / 0.75f + 1));
        Map<Long, CartItemPrice> cartItemPriceMap = new LinkedHashMap<>((int) (items.size() / 0.75f + 1));

        for (final CheckoutItem item : items) {
            skuIds.add(item.getSkuId());
            requestedQuantity.put(item.getSkuId(), item.getQuantity());

            CartItemPrice cartItemPrice = new CartItemPrice();
            cartItemPrice.setSkuId(item.getSkuId());
            cartItemPriceMap.put(item.getSkuId(), cartItemPrice);

        }

        validateInventory(skuIds, requestedQuantity);


        PricingResult pricingResult = executePricing(customerId, requestedQuantity);

        for (final ItemPrice itemPrice : pricingResult.getItemPrices()) {

            CartItemPrice cartItemPrice = cartItemPriceMap.get(itemPrice.getId());
            if (cartItemPrice != null) {
                cartItemPrice.setSalePrice(itemPrice.getSalePrice());
                cartItemPrice.setPrice(itemPrice.getPrice());
                cartItemPrice.setTotalSalePrice(itemPrice.getTotalSalePrice());
                cartItemPrice.setTotalPrice(itemPrice.getTotalPrice());
                cartItemPrice.setQuantity(itemPrice.getQuantity());

            }


        }
        UpdateCartResult result = new UpdateCartResult();
        result.setItems(cartItemPriceMap.values());
        result.setPrice(pricingResult.getPrice());
        result.setSalePrice(pricingResult.getSalePrice());


        return result;


    }

    private PricingResult executePricing(final long customerId, final Map<Long, Integer> requestedQuantity) {
        PricingContext pricingContext = new PricingContext();
        pricingContext.setCustomerId(customerId);
        pricingContext.setSkus(requestedQuantity);
        return pricingService.executePricing(pricingContext);
    }

    private Map<Long, Integer> validateInventory(final Collection<Long> skuIds, final Map<Long, Integer> requestedQuantity) {
        Map<Long, Integer> inventories = inventoryService.getInventory(skuIds);
        if (inventories.isEmpty()) {
            throw new InsufficientInventoryException("no inventory found", skuIds);
        }

        for (final Map.Entry<Long, Integer> entry : requestedQuantity.entrySet()) {
            Integer inventory = inventories.get(entry.getKey());
            if (inventory == null) {
                throw new InvalidSkusException(Collections.singleton(entry.getKey()), "Some requested skus are not invalid!");
            }


            if (inventory < entry.getValue()) {
                throw new InsufficientInventoryException("Insufficient inventory for sku " + entry.getKey(), Collections.singleton(entry.getKey()));

            }
        }
        return inventories;
    }

    @Override
    public CartInfo initCart(final long customerId, final Collection<CheckoutItem> items) {

        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("items  are null or empty!");
        }

        Collection<Long> skuIds = new ArrayList<>(items.size());
        Map<Long, Integer> requestedQuantity = new HashMap<>((int) (items.size() / 0.75f + 1));
        Map<Long, CartItemInfo> cartItemInfoMap = new LinkedHashMap<>((int) (items.size() / 0.75f + 1));

        for (final CheckoutItem item : items) {
            skuIds.add(item.getSkuId());
            requestedQuantity.put(item.getSkuId(), item.getQuantity());

            CartItemInfo cartItemInfo = new CartItemInfo();
            cartItemInfo.setSkuId(item.getSkuId());
            cartItemInfo.setQuantity(item.getQuantity());

            cartItemInfoMap.put(item.getSkuId(), cartItemInfo);

        }

        Map<Long, Integer> inventory = validateInventory(skuIds, requestedQuantity);


        Collection<SkuInfo> skuInfos = productInfoQueryRepository.queryActiveSkuInfos(skuIds);

        validateSkus(skuInfos, requestedQuantity);


        for (final SkuInfo skuInfo : skuInfos) {
            CartItemInfo cartItemInfo = cartItemInfoMap.get(skuInfo.getId());
            if (cartItemInfo != null) {
                cartItemInfo.setProductId(skuInfo.getProductId());
                cartItemInfo.setMobileImage(skuInfo.getMobileImage());
                cartItemInfo.setPcImage(skuInfo.getPcImage());
                cartItemInfo.setSkuNumber(skuInfo.getSkuNumber());
                cartItemInfo.setName(skuInfo.getName());
                cartItemInfo.setImageUrl(skuInfo.getImageUrl());

                Integer stock = inventory.get(cartItemInfo.getSkuId());
                cartItemInfo.setStock(stock);


            }


        }


        PricingResult pricingResult = executePricing(customerId, requestedQuantity);

        CartInfo cart = new CartInfo();
        for (final ItemPrice itemPrice : pricingResult.getItemPrices()) {

            CartItemInfo cartItemInfo = cartItemInfoMap.get(itemPrice.getId());
            if (cartItemInfo != null) {
                cartItemInfo.setSalePrice(itemPrice.getSalePrice());
                cartItemInfo.setPrice(itemPrice.getPrice());
                cartItemInfo.setTotalSalePrice(itemPrice.getTotalSalePrice());
                cartItemInfo.setTotalPrice(itemPrice.getTotalPrice());
                cartItemInfo.setQuantity(itemPrice.getQuantity());
            }


        }

        cart.setItems(cartItemInfoMap.values());
        cart.setPrice(pricingResult.getPrice());
        cart.setSalePrice(pricingResult.getSalePrice());


        return cart;
    }

    private void validateSkus(Collection<SkuInfo> loadedSkus, Map<Long, Integer> requestedSkus) {


        if (loadedSkus.isEmpty()) {
            throw new InvalidSkusException(requestedSkus.keySet(), "All requested skus are not invalid!");
        }


        Set<Long> loadedSkuIds = loadedSkus.isEmpty() ? Collections.<Long>emptySet() : new HashSet<Long>((int) (loadedSkus.size() / 0.75f + 1));

        for (final SkuInfo skuInfo : loadedSkus) {
            loadedSkuIds.add(skuInfo.getId());
        }


        Collection<Long> missing = null;

        for (final Long skuId : requestedSkus.keySet()) {
            if (!loadedSkuIds.contains(skuId)) {
                if (missing == null) {
                    missing = new HashSet<>((int) (requestedSkus.size() / 0.75f) + 1);
                }
                missing.add(skuId);
            }
        }

        if (missing != null && !missing.isEmpty()) {
            throw new InvalidSkusException(missing, "Some requested skus are not invalid!");

        }

    }

    @Override
    public void applyPayment(final long orderId, final PaymentGatewayResponse response) {

        Order order = orderRepository.findOne(orderId);
        if (order == null) {
            LOGGER.info("Attempted to apply PaymentGatewayResponse to order " + orderId + ", but was not found");
            return;
        }

        processOrderPaymentService.processOrderPayment(order, response);

        eventPublisher.publish(new OrderPaidEvent(order.getId(), order.getCustomerId(), order.getPrice(), order.getSalePrice(), OrderUtils.toEventItems(order)));


    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("1"));
    }
}
