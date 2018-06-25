package com.fongwell.satchi.crm.core.customer.factory;


import com.fongwell.satchi.crm.core.customer.domain.aggregate.Customer;
import com.fongwell.satchi.crm.core.customer.dto.CustomerData;
import com.fongwell.support.utils.StringBuilderHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by docker on 8/1/17.
 */
@Service("customerFactory")
public class CustomerFactoryImpl implements CustomerFactory {


    private static final int MIN_DIGITS = 6;

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public Customer createCustomer(CustomerData data) {

        int sequence = jdbcTemplate.execute("select nextval('fw_customer_number_seq')", new PreparedStatementCallback<Integer>() {

            @Override
            public Integer doInPreparedStatement(final PreparedStatement ps) throws SQLException, DataAccessException {
                ResultSet rs = ps.executeQuery();
                rs.next();
                return (int) rs.getLong(1);
            }
        });

        StringBuilder number = StringBuilderHolder.get();

        String sequenceNum = String.valueOf(sequence);
        int length = sequenceNum.length();

        number.append("CM").append("-");

        while (length < MIN_DIGITS) {
            number.append("0");
            length++;
        }
        number.append(sequenceNum);

        return new Customer(number.toString(), data);
    }
}
