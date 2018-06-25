package com.fongwell.satchi.crm.api.controller.admin;

import com.fongwell.satchi.crm.api.Payload;
import com.fongwell.satchi.crm.core.order.domain.dto.ShippingRequest;
import com.fongwell.satchi.crm.core.order.domain.value.OrderState;
import com.fongwell.satchi.crm.core.order.query.mapper.AdminOrderQueryMapper;
import com.fongwell.satchi.crm.core.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;

/**
 * Created by docker on 5/7/18.
 */
@RestController
@RequestMapping("/api/admin/orders")
public class OrderApi {

    @Autowired
    private AdminOrderQueryMapper adminOrderQueryMapper;

    @Resource(name = "orderService")
    private OrderService orderService;

    @GetMapping("")
    public Payload orders(@RequestParam(required = false, value = "states") Collection<OrderState> states,
                          @RequestParam(required = false, value = "start") Long start,
                          @RequestParam(required = false, value = "end") Long end,
                          @RequestParam(required = false, value = "from", defaultValue = "0") int from,
                          @RequestParam(required = false, value = "size", defaultValue = "20") int size
    ) {

        Date startDate = start == null ? null : new Date(start);
        Date endDate = end == null ? null : new Date(end);

        return new Payload(adminOrderQueryMapper.queryOrders(states, startDate, endDate, from, size));

    }

    @PostMapping("/ship/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void ship(@PathVariable long id, @RequestParam String trackingNumber) {
        ShippingRequest request = new ShippingRequest();
        request.setTrackingNumber(trackingNumber);
        orderService.shipOrder(id, request);

    }

    @PostMapping("/complete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void complete(@PathVariable long id) {

        orderService.completeOrder(id);

    }


    @GetMapping("/{id}")
    public Payload get(@PathVariable long id) {
        return new Payload(adminOrderQueryMapper.findOrder(id));
    }

    @GetMapping("/count")
    public Payload count(@RequestParam(required = false, value = "states") Collection<OrderState> states,
                         @RequestParam(required = false, value = "start") Long start,
                         @RequestParam(required = false, value = "end") Long end
    ) {

        Date startDate = start == null ? null : new Date(start);
        Date endDate = end == null ? null : new Date(end);

        return new Payload(adminOrderQueryMapper.countOrders(states, startDate, endDate));

    }
}
