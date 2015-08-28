package com.vz.bs.re;

public interface IVESBill {

	public abstract double calculateRateOnUsage(double usage, int billingPeriod,
			int daysInMonth);

	public abstract double calculateDiscount(double discount, double amount);

	public abstract double calculatePenalty(int minProducts, int maxProducts);

	public abstract double calculateTaxRates(int productId, int stateCode);

	public abstract double calculateNonRecurringCharges();

	public abstract double calculateMonthlyRecurringCharges();

}