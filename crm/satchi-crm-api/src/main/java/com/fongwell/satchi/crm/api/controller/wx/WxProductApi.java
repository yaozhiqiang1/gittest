package com.fongwell.satchi.crm.api.controller.wx;

import com.fongwell.base.rest.Payload;
import com.fongwell.satchi.crm.api.authentication.wx.WxCustomerContext;
import com.fongwell.satchi.crm.core.common.State;
import com.fongwell.satchi.crm.core.common.error.DataNotFoundException;
import com.fongwell.satchi.crm.core.common.query.RequestData;
import com.fongwell.satchi.crm.core.product.query.BookmarkQueryService;
import com.fongwell.satchi.crm.core.product.query.ProductQueryMapper;
import com.fongwell.satchi.crm.core.product.query.WxProductQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by roman on 18-4-4.
 */
@RestController
@RequestMapping("/api/wx/product")
public class WxProductApi {


    @Resource(name = "bookmarkQueryService")
    private BookmarkQueryService bookmarkQueryService;

    @Autowired
    private WxProductQueryMapper wxProductQueryMapper;

    @GetMapping
    public Payload findAll(@RequestParam(required = false) String query,
                           @RequestParam(required = false) Long categoryId,
                           @RequestParam(required = false) Long seriesId,
                           @RequestParam(required = false) RequestData.SortFieldParam sortField,
                           @RequestParam(required = false, defaultValue = "false") boolean desc,
                           @RequestParam(required = false, defaultValue = "0") int from,
                           @RequestParam(required = false, defaultValue = "20") int size) {
        RequestData request = new RequestData(query, categoryId == null ? null : Collections.singleton(categoryId), seriesId == null ? null : Collections.singleton(seriesId), from, size);
        request.sort(sortField == null ? null : sortField.name(), desc);
        Collection<Map> products = wxProductQueryMapper.findAll(request);
        if (products.isEmpty()) {
            return new Payload(Collections.emptyList());
        }

        Collection<Long> ids = new HashSet<>((int) (products.size() / 0.75f) + 1);
        Map<Long, Map> productMap = new HashMap<>((int) (products.size() / 0.75f) + 1);
        for (final Map product : products) {
            Long id = (Long) product.get("id");
            ids.add(id);
            productMap.put(id, product);
        }


        if(WxCustomerContext.getUser() != null) {
            Collection<Long> bookmarked = bookmarkQueryService.checkBookmarked(WxCustomerContext.getUser().getCustomerId(), ids);
            for (final Long mark : bookmarked) {

                Map product = productMap.get(mark);
                if (product != null) {
                    product.put("bookmarked", true);
                }

            }

        }
        return new Payload(products);
    }

    @GetMapping("/count")
    public Payload count(@RequestParam(required = false) String query,
                         @RequestParam(required = false) Long categoryId) {
        RequestData request = new RequestData(query, categoryId == null ? null : Collections.singleton(categoryId), null, Collections.singleton(State.enable.name()), 0, 0);
        return new Payload(wxProductQueryMapper.count(request));
    }

    @GetMapping("/{id}")
    public Payload get(@PathVariable Long id) {
        Map data = wxProductQueryMapper.get(id);

        if (data == null) {
            throw new DataNotFoundException("Product not found");
        }

        return new Payload(data);
    }

}
