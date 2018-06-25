package com.fongwell.satchi.crm.wx.sdk.token;

import com.foxinmy.weixin4j.model.Token;

/**
 * Created by docker on 11/23/17.
 */
public class JpaTokenStore implements TokenStore {

    private TokenEntityRepository tokenEntityRepository;

    public JpaTokenStore(TokenEntityRepository tokenEntityRepository) {
        this.tokenEntityRepository = tokenEntityRepository;
    }

    @Override
    public Token lookup(String s) {

        TokenEntity token = tokenEntityRepository.findOne(s);
        return token == null ? null : token.toToken();
    }

    @Override
    public void caching(String s, Token token) {
        TokenEntity tokenEntity = tokenEntityRepository.findOne(s);
        if (tokenEntity == null) {
            tokenEntityRepository.save(new TokenEntity(s, token));
        } else {
            tokenEntity.merge(token);
            tokenEntityRepository.save(tokenEntity);
        }

    }

    @Override
    public Token evict(String s) {

        TokenEntity token = tokenEntityRepository.findOne(s);
        if (token == null) {
            return null;
        }
        tokenEntityRepository.delete(token);
        return token.toToken();
    }

    @Override
    public void clear() {
        tokenEntityRepository.deleteAll();

    }
}
