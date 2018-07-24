package com.fongwell.satchi.crm.api.controller.wx.store;

import com.fongwell.base.snowflake.Snowflake;
import com.fongwell.satchi.crm.api.Payload;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by docker on 5/23/18.
 */
@RestController
@RequestMapping("/api/wx/store/messages")
public class MessageApi {

    @GetMapping("")
    public Payload messages(@RequestParam(value = "from", required = false, defaultValue = "0") int from,
                            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

        Collection<Map> result = new ArrayList<>();

        result.add(createItem());
        result.add(createItem());
        result.add(createItem());
        result.add(createItem());
        result.add(createItem());
        result.add(createItem());

        return new Payload(result);

    }


    @PostMapping("/push")
    @ResponseStatus(HttpStatus.OK)
    public void push(@RequestBody PushRequest request) {

    }


    @GetMapping("/detail")
    public Payload detail(@RequestParam long id) {

        Map item = new HashMap();
        item.put("title", "test tile");
        item.put("body", "<p>test body</p>");
        return new Payload(item);


    }

    private Map createItem() {
        Map item = new HashMap();
        item.put("id", Snowflake.id());
        item.put("image", "https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=f7cfb5c46f380cd7f213aabfc02dc651/9345d688d43f8794e373025ad81b0ef41bd53a31.jpg");
        item.put("title", "test title");
        item.put("createdDate", System.currentTimeMillis());
        return item;
    }

    public static class PushRequest {
        private Collection<Long> customerIds;
        private long messageId;

        public Collection<Long> getCustomerIds() {
            return customerIds;
        }

        public void setCustomerIds(final Collection<Long> customerIds) {
            this.customerIds = customerIds;
        }

        public long getMessageId() {
            return messageId;
        }

        public void setMessageId(final long messageId) {
            this.messageId = messageId;
        }
    }

}
