package com.ericsson.corp;

import junit.framework.TestCase;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by prasad on 21/12/15.
 * Test EricssonCorporation for different pay rates and hours
 */
public class EricssonCorporationTest {

    @Test
    public void testCalculateWeeklySalaryNormal() throws Exception {
        EricssonCorporation ericssonCorporation = new EricssonCorporation();

        assertEquals("The weekly pay for 8.0 x 35 hrs should be 280.00$",
                ericssonCorporation.calculateWeeklySalary(Money.of(CurrencyUnit.CAD,
                8.0), 35.0f), (Money.of(CurrencyUnit.CAD, 280.0)));
    }

    @Test(expected = MinimumPayRateExceededException.class)
    public void testCalculateWeeklySalaryInvalidRate() throws Exception {
        EricssonCorporation ericssonCorporation = new EricssonCorporation();
        ericssonCorporation.calculateWeeklySalary(Money.of(CurrencyUnit.CAD, 7.99), 40.0f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateWeeklySalaryInvalidHours() throws Exception {
        EricssonCorporation ericssonCorporation = new EricssonCorporation();
        ericssonCorporation.calculateWeeklySalary(Money.of(CurrencyUnit.CAD, 8.00), -10.0f);
    }

    @Test
    public void testCalculateWeeklySalaryMaxHours() throws Exception {
        EricssonCorporation ericssonCorporation = new EricssonCorporation();

        assertEquals("The weekly pay for 8.0 x 35 hrs should be 560.00$",
                ericssonCorporation.calculateWeeklySalary(Money.of(CurrencyUnit.CAD,
                        8.0), 75.0f), (Money.of(CurrencyUnit.CAD, 560.00)));
    }
}