package com.ericsson.corp;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import java.math.RoundingMode;

/**
 * Created by prasad on 21/12/15.
 * EricssonCorporation class provides functionality to calculate the
 * weekly payments for workers based on their respective hourly rates.
 * The payments calculated are subjected to:
 * Number of hours worked (WORK_HRS_PER_WEEK)
 * Maximum number of hours per week considered (MAX_WORK_HRS_PER_WEEK)
 * The factor payed for overtime work (OVERTIME_PAY_FACTOR)
 * And the minimum hourly rate accepted by the system (MIN_PAY_PER_HR)
 *
 */
public class EricssonCorporation {
    /**
     * The number of working hours considered per week as regular
     */
    public static final int WORK_HRS_PER_WEEK = 40;

    /**
     * The number of maximum working hours considered per week
     */
    public static final int MAX_WORK_HRS_PER_WEEK = 60;

    /**
     * The factor of payment for over time work hours
     */
    public static final float OVERTIME_PAY_FACTOR = 1.5f;

    /**
     * The minimum pay rate accepted by the system. This is according to the
     * Commission des normes du travail of the Quebec province
     */
    public static final Money MIN_PAY_PER_HR = Money.of(CurrencyUnit.CAD, 8.00);

    /**
     * Calculate the salary for the given hourly rate and the number of hours worked
     * @param hourlyRate    hourly rate of the employee
     * @param hours number of hours worked
     * @return  the payment for the week
     * @throws MinimumPayRateExceededException
     */
    public Money calculateWeeklySalary(Money hourlyRate, float hours) throws MinimumPayRateExceededException {
        // hourly rate < minimum hourly rate.
        if (hourlyRate.compareTo(MIN_PAY_PER_HR) < 0)
            throw new MinimumPayRateExceededException(MIN_PAY_PER_HR, hourlyRate);

        // hours given are invalid value
        if (hours < 0.0f)
            throw new IllegalArgumentException(String.valueOf(hours));

        // hours exceeds the maximum hours paid per week. reset to maximum hours
        if (hours > MAX_WORK_HRS_PER_WEEK)
            hours = MAX_WORK_HRS_PER_WEEK;

        // No over time hours. pay with regular rate
        if (hours < WORK_HRS_PER_WEEK) {
            return hourlyRate.multipliedBy(hours, RoundingMode.UNNECESSARY);
        }
        else {
            // pay for overtime hours with OVERTIME_PAY_FACTOR
            return hourlyRate.multipliedBy((WORK_HRS_PER_WEEK), RoundingMode.UNNECESSARY).plus(
                    hourlyRate.multipliedBy((hours - WORK_HRS_PER_WEEK) * OVERTIME_PAY_FACTOR,
                            RoundingMode.UNNECESSARY));
        }
    }

    /**
     * Print payment details for a given employee
     * @param employee  employee name
     * @param hourlyRate    hourly rate of the employee
     * @param hours number of hours worked
     */
    public void printEmployeePayment(String employee, Money hourlyRate, float hours) {
        try {
            Money payment = this.calculateWeeklySalary(hourlyRate, hours);
            StringBuilder details = new StringBuilder();
            details.append(employee);
            details.append(":\t");
            details.append(hourlyRate.toString());
            details.append("\t");
            details.append(hours);
            details.append("\t");
            details.append(payment.toString());
            System.out.println(details.toString());
        } catch (MinimumPayRateExceededException e) {
            System.out.println("Wrong minimum rate for the employee :" + employee + "\n" +  e.toString());
        }
    }
}
