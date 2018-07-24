package com.fongwell.satchi.crm.api.controller.admin;

import com.fongwell.satchi.crm.api.Payload;
import com.fongwell.satchi.crm.core.common.KeyValue;
import com.fongwell.satchi.crm.core.credit.query.mapper.CreditQueryMapper;
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

    //会员列表
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
   
    //会员总数
    @GetMapping("/count")
    public Payload count(@RequestParam(required = false) String query) {
        return new Payload(adminCustomerQueryMapper.countCustomers(query));
    }

    //会员资料
    @GetMapping("/detail")
    public Payload detail(@RequestParam long id) {
        Map detail = adminCustomerQueryMapper.getCustomer(id);
        if (detail == null) {
            return Payload.empty;
        }

        return new Payload(detail);
    }

    //删除会员
    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam long id) {

        customerService.deleteCustomer(id);
    }

    //会员消费指标
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

    //购买记录
    @GetMapping("/orderHistory")
    public Payload orderHistory(@RequestParam long id, @RequestParam(required = false, defaultValue = "0") int from,
                                @RequestParam(required = false, defaultValue = "20") int size) {
        return new Payload(orderStatsQueryMapper.queryOrderHistory(id, from, size));

    }

    //新增会员
    @PostMapping("/create")
    public Payload create(@RequestBody CustomerData data) {
        return new Payload(customerService.createCustomer(data).getId());
    }
    
    //更新会员资料
    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody CustomerData data) {
        customerService.updateCustomer(data.getId(), data);
    }

    //会员标签
    @GetMapping("/tags")
    public Payload tags(@RequestParam long id) {
        return new Payload(customerTagQueryMapper.queryTagsForCustomer(id));
    }

    //会员标签
    @PostMapping("/test")
    public void test(@RequestParam String id) {
    	System.out.println("---------------------------------------------");
    	System.out.println("-------------id:"+id+"--------------------");
    	System.out.println("---------------------------------------------");
    }
    //会员标签
    @GetMapping("/tests")
    public void tests() {
    	System.out.println("---------------------------------------------");
    	System.out.println("-------------i---------------------------");
    	System.out.println("---------------------------------------------");
    }
    




}
