package com.fongwell.satchi.crm.api.controller.wx;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fongwell.satchi.crm.api.Payload;
import com.fongwell.satchi.crm.api.authentication.wx.WxCustomerContext;
import com.fongwell.satchi.crm.core.customer.domain.value.AddressValue;
import com.fongwell.satchi.crm.core.order.query.OrderQueryRepository;
import com.fongwell.satchi.crm.core.order.query.dto.OrderDetail;
import com.fongwell.satchi.crm.core.order.service.OrderService;

/**
 * Created by docker on 4/23/18.
 */
@RequestMapping("/api/wx/orders")
@RestController
public class WxOrderApi {

    @Resource(name = "orderQueryRepository")
    private OrderQueryRepository orderQueryRepository;

    @Resource(name = "orderService")
    private OrderService orderService;


    @GetMapping("/{id}")
    public Payload orderDetail(@PathVariable long id) {
        OrderDetail orderDetail = orderQueryRepository.queryOrderDetail(id);
        AddressValue shippingAddress = orderDetail.getShippingAddress();
        String district = shippingAddress.getDistrict();
        if (!StringUtils.isNotBlank(district)){
            shippingAddress.setDistrict("");
        }
        return new Payload(orderDetail);
    }

    @GetMapping("")
    public Payload orders(@RequestParam(name = "from", defaultValue = "0", required = false) int from,
                          @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        return new Payload(orderQueryRepository.queryOrders(WxCustomerContext.getUser().getCustomerId(), null, from, size));
    }


    @PostMapping("/cancel/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void cancel(@PathVariable long id) {
        orderService.cancelOrder(id);
    }

    @PostMapping("/complete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void complete(@PathVariable long id) {
        orderService.completeOrder(id);
    }
    
    
    @GetMapping("/unship/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void unship(@PathVariable long id) {
        orderService.unship(id);
    }
}
