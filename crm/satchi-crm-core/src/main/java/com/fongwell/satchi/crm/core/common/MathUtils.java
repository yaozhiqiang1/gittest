package com.fongwell.satchi.crm.core.common;

import java.math.BigDecimal;

/**
 * Created by docker on 5/22/18.
 */
public class MathUtils {

    public static final BigDecimal divide(BigDecimal divider, BigDecimal divisor) {

        if (divider == null || divisor == null || BigDecimal.ZERO.compareTo(divisor) == 0) {
            return BigDecimal.ZERO;
        }

        return divider.divide(divisor, 2, BigDecimal.ROUND_FLOOR);

    }
}
