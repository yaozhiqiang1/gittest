package com.fongwell.satchi.crm.core.product.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by docker on 4/23/18.
 */
@Service("bookmarkProductService")
public class BookmarkProductServiceImpl implements BookmarkProductService, InitializingBean {


    private static final String CREATE_TABLE_SQL = "create table if not exists crm_product_bookmark(customer_id bigint,product_id bigint, createdDate timestamp," +
            " primary key(customer_id,product_id)) ";

    private static final String INSERT_SQL = "insert into crm_product_bookmark (customer_id,product_id,createddate) values (?,?,?)";

    private static final String DELETE_SQL = "delete from crm_product_bookmark where customer_id = ? and product_id = ?";

    private static final String SELECT_SQL = "select count(1) from crm_product_bookmark where customer_id = ? and product_id = ?";


    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public void bookmark(final long customerId, final long productId) {
        Integer count = jdbcTemplate.queryForObject(SELECT_SQL, Integer.class, customerId, productId);
        if (count == null || count == 0) {
            jdbcTemplate.update(INSERT_SQL, new Object[]{customerId, productId, new java.sql.Timestamp(System.currentTimeMillis())});
        }

    }

    @Override
    public void unmark(final long customerId, final long productId) {

        jdbcTemplate.update(DELETE_SQL, new Object[]{customerId, productId});

    }

    @Override
    public void afterPropertiesSet() throws Exception {

        try {
            jdbcTemplate.execute(CREATE_TABLE_SQL);
        } catch (DataAccessException e) {

        }

    }
}
