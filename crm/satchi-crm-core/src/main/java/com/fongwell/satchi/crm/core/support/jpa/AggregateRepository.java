package com.fongwell.satchi.crm.core.support.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by docker on 2/25/18.
 */
@NoRepositoryBean
public interface AggregateRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
}
