package com.fongwell.satchi.crm.api.controller.wx;

import com.fongwell.satchi.crm.api.Payload;
import com.fongwell.satchi.crm.api.authentication.wx.WxCustomerContext;
import com.fongwell.satchi.crm.core.customer.domain.value.AddressValue;
import com.fongwell.satchi.crm.core.order.query.OrderQueryRepository;
import com.fongwell.satchi.crm.core.order.query.dto.OrderDetail;
import com.fongwell.satchi.crm.core.order.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
}
