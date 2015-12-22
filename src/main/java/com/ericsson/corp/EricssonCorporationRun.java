package com.ericsson.corp;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

/**
 * Created by prasad on 21/12/15.
 * A Test class to check functionality of EricssonCorporation
 */
public class EricssonCorporationRun {


    public static void main(String[] args) {
        EricssonCorporation ericssonCorporation = new EricssonCorporation();
        Money rates[] = {Money.of(CurrencyUnit.CAD, 7.50),
                Money.of(CurrencyUnit.CAD, 8.50),
                Money.of(CurrencyUnit.CAD, 9.5)
        };
        float hours[] = {45.0f, 40.00f, 35.0f};
        for (int i = 0; i < 5; ++i) {
            ericssonCorporation.printEmployeePayment("Employee " + i, rates[i], hours[i]);
        }
    }
}
