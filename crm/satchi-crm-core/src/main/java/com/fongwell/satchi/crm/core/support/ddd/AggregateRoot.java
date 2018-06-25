package com.fongwell.satchi.crm.core.support.ddd;

import java.io.Serializable;

/**
 * Created by docker on 11/26/17.
 */
public interface AggregateRoot extends Serializable {

    Serializable getId();
}
