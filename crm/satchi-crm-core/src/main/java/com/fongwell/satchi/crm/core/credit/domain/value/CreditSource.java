package com.fongwell.satchi.crm.core.credit.domain.value;

import com.fongwell.base.resource.domain.StorageType;
import com.fongwell.support.EnumType;

/**
 * Created by docker on 5/7/18.
 */
public class CreditSource extends EnumType {
    public static final CreditSource REGISTER = new CreditSource("REGISTER", "REGISTER");
    public static final CreditSource CHECKIN = new CreditSource("CHECKIN", "CHECKIN");
    public static final CreditSource PURCHASE = new CreditSource("PURCHASE", "PURCHASE");


    public CreditSource(String type, String name) {
        super(type, name);
    }

    public CreditSource() {
    }


}
