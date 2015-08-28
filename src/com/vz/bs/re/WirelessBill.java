package com.vz.bs.re;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vz.bs.helper.ConnectionGetter;

public class WirelessBill implements IWirelessBill {
	Connection con;
	/* (non-Javadoc)
	 * @see com.vz.bs.re.IWirelessBill#rateOnUsage(double, int, int)
	 */
	@Override
	public double calculateRateOnUsage(double usage, int billingPeriod,
			int daysInMonth) {
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

	/* (non-Javadoc)
	 * @see com.vz.bs.re.IWirelessBill#TaxRates(int, int, int, int)
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


	/* (non-Javadoc)
	 * @see com.vz.bs.re.IWirelessBill#nonRecurringCharges()
	 */
	@Override
	public double calculateNonRecurringCharges()
	{
		double charges=5000;
		return charges;
	}
	
	/* (non-Javadoc)
	 * @see com.vz.bs.re.IWirelessBill#monthlyRecurringCharges()
	 */
	@Override
	public double calculateMonthlyRecurringCharges()
	{
		double charges=5000;
		return charges;
	}
	
}
