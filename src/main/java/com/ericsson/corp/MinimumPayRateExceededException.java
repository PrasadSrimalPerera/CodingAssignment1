package com.ericsson.corp;

import org.joda.money.Money;

/**
 * Created by prasad on 21/12/15.
 * MinimumPayRateExceededException is an exception type to be used
 * when the given input hourly rate is lower than the accepted minimum hourly
 * rate by the system.
 */
public class MinimumPayRateExceededException extends Exception {
    public MinimumPayRateExceededException(Money basePay, Money givenPay) {
        super(givenPay.toString() + " < " + basePay.toString());
    }
}
