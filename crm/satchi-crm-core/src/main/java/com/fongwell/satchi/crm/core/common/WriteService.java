package com.fongwell.satchi.crm.core.common;

import com.fongwell.satchi.crm.core.support.ddd.AggregateRoot;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by docker on 11/26/17.
 */
@Transactional
public interface WriteService<T extends AggregateRoot, ID extends Serializable, DTO extends Serializable> {

    T create(DTO data);

    T update(ID id, DTO data);

    void delete(ID id);

}
