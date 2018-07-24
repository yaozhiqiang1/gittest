package com.fongwell.satchi.crm.api.controller.wx.store;

import com.fongwell.satchi.crm.api.Payload;
import com.fongwell.satchi.crm.api.authentication.wx.WxStoreContext;
import com.fongwell.satchi.crm.core.customer.domain.query.CustomerTagSearchService;
import com.fongwell.satchi.crm.core.customer.domain.service.CustomerTagService;
import com.fongwell.satchi.crm.core.customer.query.mapper.WxCustomerQueryMapper;
import com.fongwell.satchi.crm.core.customer.service.CustomerService;
import com.fongwell.satchi.crm.core.order.query.dto.OrderStats;
import com.fongwell.satchi.crm.core.order.query.mapper.OrderStatsQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.*;

/**
 * Created by docker on 5/17/18.
 */

@RestController
@RequestMapping("/api/wx/store/customers")
public class CustomerApi {

    @Autowired
    private WxCustomerQueryMapper wxCustomerQueryMapper;

    @Autowired
    private CustomerTagSearchService customerTagSearchService;

    @Autowired
    private CustomerTagService customerTagService;

    @Autowired
    private OrderStatsQueryMapper orderStatsQueryMapper;

    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public Payload customers(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "tags", required = false) Collection<String> tags,
            @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "from", required = false, defaultValue = "0") int from,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

        Collection<Long> customerIds = null;
        if (tags != null && !tags.isEmpty()) {
            customerIds = customerTagSearchService.searchCustomersWithTags(tags);
        }


        Collection<Map> customers = wxCustomerQueryMapper.queryStoreCustomers(null, customerIds, query, from, size);
        if (customers.isEmpty()) {
            return new Payload(Collections.emptyList());
        }

        Map<Long, Map> customerMap = new HashMap<>(customers.size(), 2f);
        for (final Map customer : customers) {
            customerMap.put((Long) customer.get("id"), customer);
        }

        Map<Long, Collection<String>> customerTags = customerTagSearchService.searchTags(customerMap.keySet());
        for (final Map.Entry<Long, Collection<String>> entry : customerTags.entrySet()) {

            Map customer = customerMap.get(entry.getKey());
            if (customer != null) {
                customer.put("tags", entry.getValue());
            }
        }
        return new Payload(customerMap.values());
    }

    //会员资料
    @GetMapping("/detail")
    public Payload detail(@RequestParam long id) {

        Map customer = wxCustomerQueryMapper.queryDetail(id);
        if (customer == null) {
            return Payload.empty;
        }

        Map<Long, Collection<String>> tags = customerTagSearchService.searchTags(Collections.singleton(id));
        if (!tags.isEmpty()) {
            customer.put("tags", tags.entrySet().iterator().next().getValue());
        }

        OrderStats stats = orderStatsQueryMapper.queryStats(id);
        if (stats != null) {
            customer.put("expensePerOrder", stats.getExpensePerOrder());
            customer.put("orders", stats.getOrders());
            customer.put("expensePerItem", stats.getExpensePerItem());
            customer.put("expenses", stats.getExpenses());
        }
        return new Payload(customer);
    }

    //会员标签
    @PostMapping("/tags")
    @ResponseStatus(HttpStatus.OK)
    public void saveTags(@RequestBody SaveTagsRequest request) {
        customerTagService.saveTags(request.getCustomerId(), request.getTags());
    }
    
    //购买记录
    @GetMapping("/orderHistory")
    public Payload orderHistory(
            @RequestParam long id,
            @RequestParam(value = "from", required = false, defaultValue = "0") int from,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return new Payload(orderStatsQueryMapper.queryOrderItemHistory(id, from, size));
    }
    
    //设置为重要
    @PostMapping("/markImportant")
    @ResponseStatus(HttpStatus.OK)
    public void markImportant(@RequestParam long customerId) {
        customerService.markImportant(customerId, WxStoreContext.getStaff().getStoreId());

    }
    
    //取消为重要
    @PostMapping("/unmarkImportant")
    @ResponseStatus(HttpStatus.OK)
    public void unmarkImportant(@RequestParam long customerId) {
        customerService.unmarkImportant(customerId, WxStoreContext.getStaff().getStoreId());
    }


    public static class SaveTagsRequest implements Serializable {
        private long customerId;
        private Set<String> tags;

        public long getCustomerId() {
            return customerId;
        }

        public void setCustomerId(final long customerId) {
            this.customerId = customerId;
        }

        public Set<String> getTags() {
            return tags;
        }

        public void setTags(final Set<String> tags) {
            this.tags = tags;
        }
    }


}
