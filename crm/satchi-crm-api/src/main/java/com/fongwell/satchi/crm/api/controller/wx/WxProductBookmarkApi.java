package com.fongwell.satchi.crm.api.controller.wx;

import com.fongwell.satchi.crm.api.Payload;
import com.fongwell.satchi.crm.api.authentication.wx.WxCustomerContext;
import com.fongwell.satchi.crm.core.product.query.BookmarkQueryService;
import com.fongwell.satchi.crm.core.product.service.BookmarkProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * Created by docker on 4/23/18.
 */
@RequestMapping("/api/wx/bookmarks")
@RestController
public class WxProductBookmarkApi {

    @Resource(name = "bookmarkProductService")
    private BookmarkProductService bookmarkProductService;
    @Resource(name = "bookmarkQueryService")
    private BookmarkQueryService bookmarkQueryService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public void bookmark(@RequestParam long id) {
        bookmarkProductService.bookmark(WxCustomerContext.getUser().getCustomerId(), id);
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.OK)
    public void unmark(@RequestParam long id) {
        bookmarkProductService.unmark(WxCustomerContext.getUser().getCustomerId(), id);
    }

    @GetMapping("")
    public Payload bookmarks(@RequestParam(name = "from", defaultValue = "0", required = false) int from,
                             @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        return new Payload(bookmarkQueryService.queryBookmarkProducts(WxCustomerContext.getUser().getCustomerId(), from, size));
    }


    @GetMapping("/check")
    public Payload check(@RequestParam long productId) {
        return new Payload(bookmarkQueryService.isBookmarked(WxCustomerContext.getUser().getCustomerId(), productId));
    }

    @GetMapping("/checkBatch")
    public Payload checkBatch(@RequestParam Collection<Long> productIds) {
        return new Payload(bookmarkQueryService.checkBookmarked(WxCustomerContext.getUser().getCustomerId(), productIds));
    }

}
