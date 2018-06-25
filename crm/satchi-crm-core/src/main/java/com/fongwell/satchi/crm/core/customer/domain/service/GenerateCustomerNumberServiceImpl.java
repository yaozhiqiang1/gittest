package com.fongwell.satchi.crm.core.customer.domain.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by docker on 3/19/18.
 */
@Service("generateCustomerNumberService")
public class GenerateCustomerNumberServiceImpl implements GenerateCustomerNumberService, InitializingBean {

    private static final int MIN_DIGITS = 6;

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public String generate() {
        int sequence = jdbcTemplate.execute("select nextval('fw_customer_number_seq')", new PreparedStatementCallback<Integer>() {

            @Override
            public Integer doInPreparedStatement(final PreparedStatement ps) throws SQLException, DataAccessException {
                ResultSet rs = ps.executeQuery();
                rs.next();
                return (int) rs.getLong(1);
            }
        });

        StringBuilder number = new StringBuilder();

        String sequenceNum = String.valueOf(sequence);
        int length = sequenceNum.length();

        number.append("CM").append("-");

        while (length < MIN_DIGITS) {
            number.append("0");
            length++;
        }
        number.append(sequenceNum);
        return number.toString();
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        try {
            jdbcTemplate.execute("create sequence fw_customer_number_seq start 1");
        } catch (DataAccessException e) {
        }
    }
}
