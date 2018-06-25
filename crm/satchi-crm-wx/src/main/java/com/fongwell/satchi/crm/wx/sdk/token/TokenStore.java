package com.fongwell.satchi.crm.wx.sdk.token;

import com.foxinmy.weixin4j.cache.CacheStorager;
import com.foxinmy.weixin4j.model.Token;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by docker on 10/20/17.
 */
@Transactional
public interface TokenStore extends CacheStorager<Token> {


}
