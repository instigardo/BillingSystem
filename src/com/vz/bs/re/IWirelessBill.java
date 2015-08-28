package com.vz.bs.re;

public interface IWirelessBill {

	public abstract double calculateRateOnUsage(double usage, int billingPeriod,
			int daysInMonth);

	public abstract double calculateTaxRates(int productId, int stateCode);

	public abstract double calculateNonRecurringCharges();

	public abstract double calculateMonthlyRecurringCharges();

}