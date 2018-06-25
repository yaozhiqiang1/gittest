package com.fongwell.satchi.crm.api.controller.admin;

import com.fongwell.satchi.crm.api.Payload;
import com.fongwell.satchi.crm.core.common.KeyValue;
import com.fongwell.satchi.crm.core.credit.query.mapper.CreditQueryMapper;
import com.fongwell.satchi.crm.core.customer.domain.aggregate.Customer;
import com.fongwell.satchi.crm.core.customer.domain.query.CustomerTagQueryMapper;
import com.fongwell.satchi.crm.core.customer.dto.CustomerData;
import com.fongwell.satchi.crm.core.customer.query.mapper.AdminCustomerQueryMapper;
import com.fongwell.satchi.crm.core.customer.service.CustomerService;
import com.fongwell.satchi.crm.core.order.query.dto.OrderStats;
import com.fongwell.satchi.crm.core.order.query.mapper.OrderStatsQueryMapper;
import com.fongwell.support.lang.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by docker on 5/22/18.
 */
@RestController("adminCustomerApi")
@RequestMapping("/api/admin/customers")
public class CustomerApi {


    @Autowired
    private AdminCustomerQueryMapper adminCustomerQueryMapper;

    @Autowired
    private OrderStatsQueryMapper orderStatsQueryMapper;

    @Autowired
    private CustomerTagQueryMapper customerTagQueryMapper;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CreditQueryMapper creditQueryMapper;

    /**
     * 查询出会员列表相应的数据
     * @param query
     * @param from
     * @param size
     * @return
     */
    @GetMapping("")
    public Payload list(@RequestParam(required = false) String query,
                        @RequestParam(required = false, defaultValue = "0") int from,
                        @RequestParam(required = false, defaultValue = "20") int size) {
        Collection<Map> customers = adminCustomerQueryMapper.queryCustomers(query, from, size);

        if (!customers.isEmpty()) {
            Map<Long, Map> map = new HashMap<>(customers.size());
            for (final Map customer : customers) {
                map.put((Long) customer.get("id"), customer);
            }
            Collection<KeyValue> expenses = orderStatsQueryMapper.queryTotalExpenses(map.keySet());

            for (final KeyValue expense : expenses) {
                Map item = map.get(expense.getKey());
                if (item != null) {
                    item.put("totalExpense", expense.getValue());
                }

            }


        }
        return new Payload(customers);
    }

    /**
     * 查询会员总数
     * @param query
     * @return
     */
    @GetMapping("/count")
    public Payload count(@RequestParam(required = false) String query) {
        return new Payload(adminCustomerQueryMapper.countCustomers(query));
    }

    @GetMapping("/detail")
    public Payload detail(@RequestParam long id) {
        Map detail = adminCustomerQueryMapper.getCustomer(id);
        if (detail == null) {
            return Payload.empty;
        }

        return new Payload(detail);
    }

    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam long id) {

        customerService.deleteCustomer(id);
    }

    @GetMapping("/expenseSummary")
    public Payload expenseSummary(@RequestParam long id) {

        OrderStats stats = orderStatsQueryMapper.queryStats(id);
        Map result = new HashMap();
        if (stats != null) {
            result.putAll(Json.mapper.convertValue(stats, HashMap.class));
        }
        Integer credits = creditQueryMapper.queryCredits(id);
        result.put("credits", credits == null ? 0 : credits);

        return new Payload(result);


    }

    @GetMapping("/orderHistory")
    public Payload orderHistory(@RequestParam long id, @RequestParam(required = false, defaultValue = "0") int from,
                                @RequestParam(required = false, defaultValue = "20") int size) {
        return new Payload(orderStatsQueryMapper.queryOrderHistory(id, from, size));

    }


    @PostMapping("/create")
    public Payload create(@RequestBody CustomerData data) {
        return new Payload(customerService.createCustomer(data).getId());
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody CustomerData data) {
        customerService.updateCustomer(data.getId(), data);
    }

    @GetMapping("/tags")
    public Payload tags(@RequestParam long id) {
        return new Payload(customerTagQueryMapper.queryTagsForCustomer(id));
    }

}
