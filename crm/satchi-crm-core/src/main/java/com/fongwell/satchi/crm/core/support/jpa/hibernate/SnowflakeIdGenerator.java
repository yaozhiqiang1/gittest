package com.fongwell.satchi.crm.core.support.jpa.hibernate;

import com.fongwell.base.snowflake.Snowflake;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * Created by docker on 11/22/17.
 */
public class SnowflakeIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SessionImplementor sessionImplementor, Object o) throws HibernateException {
        return Snowflake.id();
    }
}
