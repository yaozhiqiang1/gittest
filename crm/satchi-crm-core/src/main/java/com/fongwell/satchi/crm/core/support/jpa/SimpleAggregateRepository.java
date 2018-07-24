package com.fongwell.satchi.crm.core.support.jpa;

import com.fongwell.satchi.crm.core.support.ddd.AbstractAggregateRoot;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by docker on 2/25/18.
 */

public class SimpleAggregateRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements AggregateRepository<T, ID> {

    private final JpaEntityInformation<T, ?> entityInformation;

    private EntityManager em;

    public SimpleAggregateRepository(JpaEntityInformation<T, ? extends Serializable> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.em = entityManager;
    }

    @Override
    @Transactional
    public <S extends T> S save(S entity) {

        if (entityInformation.isNew(entity)) {
            em.persist(entity);
            return entity;
        } else {

            if (entity instanceof AbstractAggregateRoot) {
                ((AbstractAggregateRoot) entity).preUpdate();
            }

            return em.merge(entity);
        }


    }
}

