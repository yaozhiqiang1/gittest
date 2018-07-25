package com.fongwell.satchi.crm.api.controller.wx.store;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fongwell.base.snowflake.Snowflake;
import com.fongwell.satchi.crm.api.Payload;
import com.fongwell.satchi.crm.core.brandNews.domain.aggregate.CommonUtil;
import com.fongwell.satchi.crm.core.brandNews.domain.aggregate.Material;
import com.fongwell.satchi.crm.core.brandNews.domain.aggregate.MaterialImage;
import com.fongwell.satchi.crm.core.brandNews.domain.aggregate.Token;
/**
 * Created by docker on 5/23/18.
 */
@RestController
@RequestMapping("/api/wx/store/messages")
public class MessageApi {

    @GetMapping("")
    public Payload messages(@RequestParam(value = "from", required = false, defaultValue = "0") int from,
                            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

   /*   Collection<Map> result = new ArrayList<>();

        result.add(createItem());
        result.add(createItem());
        result.add(createItem());
        result.add(createItem());
        result.add(createItem());
        result.add(createItem());*/
        Token token;
		try {
			token = CommonUtil.getToken("wx7a2d5fcb771ecf37","b4192c66fcce8bfb23809cbfa2da6739");
			List<Material> lists = CommonUtil.getMaterial(token.getAccessToken(),"news",0,10);//调用获取素材列表的方法
			List<MaterialImage> list = CommonUtil.getMaterialImage(token.getAccessToken(),"image",0,10);
			for(Material mat : lists) {
				for(MaterialImage mati : list) {
					if(mat.getMedia_id().equals(mati.getMedia_id())) {
						mat.setUpdate_time(mati.getUpdate_time());
						mat.setImage_url(mati.getUrl());
						mat.setName(mat.getName());
					}
				}
			}
			return new Payload(lists);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
        return null;

    }


    @PostMapping("/push")
    @ResponseStatus(HttpStatus.OK)
    public void push(@RequestBody PushRequest request) {

    }

    
    

    @GetMapping("/detail")
    public Payload detail(@RequestParam String id) {
/*
        Map item = new HashMap();
        item.put("title", "test tile");
        item.put("body", "<p>test body</p>");
        return new Payload(item);*/
    	Token token;
		try {
			token = CommonUtil.getToken("wx7a2d5fcb771ecf37","b4192c66fcce8bfb23809cbfa2da6739");
			List<Material> lists = CommonUtil.getMaterial(token.getAccessToken(),"news",0,10);//调用获取素材列表的方法
			for(Material mat : lists) {
				if(mat.getMedia_id().equals(id)) {
					return new Payload(mat.getContent());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;


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
