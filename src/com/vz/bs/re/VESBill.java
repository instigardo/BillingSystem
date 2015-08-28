package com.vz.bs.re;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import com.vz.bs.helper.ConnectionGetter;

public class VESBill implements IVESBill {
	Connection con;

	/*
	 * ,int services, double discount, int minProducts, int maxProducts,
	 * contract to an end ~ penalty=2(calc for max-min),
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vz.bs.re.IVESBill#rateOnUsage(double, int, int, int, double)
	 */
	@Override
	public double calculateRateOnUsage(double usage, int billingPeriod, int daysInMonth) {
		float rate = (float) 14.5;
		double usageAmount;
		if (billingPeriod < daysInMonth) {
			rate = billingPeriod * (rate / daysInMonth);
			usageAmount = usage * (rate / 100);
		} else {
			usageAmount = usage * (rate / 100);
		}
		return usageAmount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vz.bs.re.IVESBill#taxRates(int, int, int, int)
	 */
	@Override
	public double calculateTaxRates(int productId, int stateCode) {
		ConnectionGetter cg = new ConnectionGetter();
		con = cg.getConnection();
		int tax_id = 0;
		double tax_total = 0, tax_rate = 0;
		try {
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select TAX_RATE from TAX_MASTER where STATE_CODE=" + stateCode);
			while (rs.next()) {
				tax_rate = rs.getDouble("TAX_RATE");
			}
			System.out.println(tax_id + "\t" + tax_rate + "\t" + tax_total);
			return tax_rate;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tax_rate;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vz.bs.re.IVESBill#nonRecurringCharges()
	 */
	@Override
	public double calculateNonRecurringCharges() {
		double charges = 5000;
		return charges;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vz.bs.re.IVESBill#monthlyRecurringCharges()
	 */
	@Override
	public double calculateMonthlyRecurringCharges() {
		double charges = 5000;
		return charges;
	}

	@Override
	public double calculateDiscount(double discount,double amount) {
		amount=amount-(amount*(discount/100));
		return amount;
	}

	@Override
	public double calculatePenalty(int minProducts, int maxProducts) {
		double penaltyAmount;
		if(maxProducts!=minProducts)
		{
			penaltyAmount=2*(maxProducts-minProducts);
			return penaltyAmount;
			
		}
		else 
			return 0;
	}

}