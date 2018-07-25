package com.fongwell.satchi.crm.api.controller.wx.store;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.persistence.sessions.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fongwell.satchi.crm.api.Payload;
import com.fongwell.satchi.crm.api.authentication.wx.WxStoreContext;
import com.fongwell.satchi.crm.core.customer.domain.query.CustomerTagSearchService;
import com.fongwell.satchi.crm.core.customer.domain.service.CustomerTagService;
import com.fongwell.satchi.crm.core.customer.query.mapper.WxCustomerQueryMapper;
import com.fongwell.satchi.crm.core.customer.service.CustomerService;
import com.fongwell.satchi.crm.core.order.domain.aggregate.Order;
import com.fongwell.satchi.crm.core.order.query.dto.OrderStats;
import com.fongwell.satchi.crm.core.order.query.mapper.OrderQueryMapper;
import com.fongwell.satchi.crm.core.order.query.mapper.OrderStatsQueryMapper;

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
    private OrderQueryMapper orderQueryMapper;

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
    	
    	System.out.println("---------------------------------------------CustomerApi.customers");
    	System.out.println("属性：query："+query);
    	System.out.println("属性：tags："+tags);
    	System.out.println("属性：sort："+sort);
    	System.out.println("属性：sortBy："+sortBy);
    	System.out.println("属性：from："+from);
    	System.out.println("属性：size："+size);
    	System.out.println("---------------------------------------------");
        Collection<Long> customerIds = null;
        if (tags != null && !tags.isEmpty()) {
            customerIds = customerTagSearchService.searchCustomersWithTags(tags);
        }

        Collection<Map> customers;
        
        if(null != sortBy && sortBy.length()>0) {
        	System.out.println("------------querySC");
        	customers = wxCustomerQueryMapper.querySC(null, customerIds, query,sort,sortBy, from, size);
        }else {
        	System.out.println("------------queryStoreCustomers");
        	customers  = wxCustomerQueryMapper.queryStoreCustomers(null, customerIds, query, from, size);
        }
        if (customers.isEmpty()) {
            return new Payload(Collections.emptyList());
        }

        Map<Long, Map> customerMap = new LinkedHashMap<>(customers.size(), 2f);
        if(false) {
        	Map<Long,Integer> map = new HashMap<>();
        	 for (final Map customer : customers) {
        		 Long id = (Long)customer.get("id");
        		 List<Order> list = orderQueryMapper.findOrderByUser(id);
        		 map.put(id, list.size());
             }
        	 
        	 
        }
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

    	System.out.println("---------------------------------------------CustomerApi.detail");
        Map customer = wxCustomerQueryMapper.queryDetail(id);
        if (customer == null) {
            return Payload.empty;
        }

        Map<Long, Collection<String>> tags = customerTagSearchService.searchTags(Collections.singleton(id));
        if (!tags.isEmpty()) {
            customer.put("tags", tags.entrySet().iterator().next().getValue());
        }
        
        List<Order> list = orderQueryMapper.findOrderByUser(id);
        //总消费金额
        customer.put("expenses", list.size()*0.01);
        //list.get(0).getSalePrice();
        //消费次数
        float price = 0.01f;
        if(list.size()==0) {
        	price=0;
        }
        customer.put("orders", list.size());
        //平均每次消费金额
        customer.put("averageExpense", price);
        //客单价
        customer.put("expensePerOrder", list.size()*price/price);
        //件单价
        customer.put("expensePerItem", price);
        //因暂时订单全部为0.01 件单价不好计算，先默认为0.01
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
    	System.out.println("---------------------------------------------CustomerApi.tags");
        customerTagService.saveTags(request.getCustomerId(), request.getTags());
    }
    
    //购买记录
    @GetMapping("/orderHistory")
    public Payload orderHistory(
            @RequestParam long id,
            @RequestParam(value = "from", required = false, defaultValue = "0") int from,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
    	System.out.println("---------------------------------------------CustomerApi.orderHistory");
        return new Payload(orderStatsQueryMapper.queryOrderItemHistory(id, from, size));
    }
    
    //设置为重要
    @PostMapping("/markImportant")
    @ResponseStatus(HttpStatus.OK)
    public void markImportant(@RequestParam long customerId) {
    	System.out.println("---------------------------------------------CustomerApi.markImportant");
        customerService.markImportant(customerId, WxStoreContext.getStaff().getStoreId());

    }
    
    //取消为重要
    @PostMapping("/unmarkImportant")
    @ResponseStatus(HttpStatus.OK)
    public void unmarkImportant(@RequestParam long customerId) {
    	System.out.println("---------------------------------------------CustomerApi.unmarkImportant");
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
