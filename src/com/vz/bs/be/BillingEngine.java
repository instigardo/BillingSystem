package com.vz.bs.be;

import java.sql.ResultSet;

import com.vz.be.serv.SecServlet;
import com.vz.bs.helper.SQLHelper;
import com.vz.bs.puller.CustomerInfoExtractor;
import com.vz.bs.puller.ProfileJsonGet;
import com.vz.bs.puller.portfolioDateStore;

public class BillingEngine {
	String billString;
	String billCycleDate;
	String jsonCreationString[] = new String[100];
	double billedAmount = 0;
	double amountReceived = 0;
	double outstandingAmount = 0;
	public boolean isNewCustomer() {
		return newCustomer;
	}

	boolean newCustomer=false;


	public String getBillCycleDate() {
		return billCycleDate;
	}

	public void setBillCycleDate(String billCycleDate) {
		this.billCycleDate = billCycleDate;
	}

	public String getBillString() {
		return billString;
	}
	int accountNumber;
	public void calculateBill() {

		SQLHelper sqlHelp = new SQLHelper();		
		ProfileJsonGet pjget=new ProfileJsonGet();
		
		portfolioDateStore pds=new portfolioDateStore();
		billCycleDate=pds.getBdate();
		//System.out.println("pj "+accountNumber);
		try {

			ResultSet rs = sqlHelp.SELECT(
					"PAYMENTS_SUMMARY i, PAYMENTS_OUTSTANDING_SUMMARY j",
					"i.BILLED_AMOUNT, i.AMOUNT_RECEIVED,j.OUTSTANDING_AMOUNT",
					"i.ACCOUNT_NUMBER=" + accountNumber
					+ "and j.ACCOUNT_NUMBER=" + accountNumber);
			if(rs.next()){
				newCustomer=false;
				do {
					outstandingAmount = rs.getDouble("OUTSTANDING_AMOUNT");
					billedAmount = rs.getDouble("BILLED_AMOUNT")
							+ outstandingAmount;
					amountReceived = rs.getDouble("AMOUNT_RECEIVED");
				}while (rs.next());
			}
			else{
				newCustomer=true;
				outstandingAmount = 0;
				billedAmount = 0;
				amountReceived = 0;
				sqlHelp.INSERT("payments_summary", accountNumber+ ",to_date('"+ billCycleDate + "','DD/MON/YYYY'), "+ billedAmount+", "+amountReceived+","+"NULL");
				sqlHelp.INSERT("payments_outstanding_summary", accountNumber+", "+"to_date('"+ billCycleDate + "','DD/MON/YYYY') "+", "+outstandingAmount);
			}
			outstandingAmount = billedAmount - amountReceived;
			System.out.println(outstandingAmount+" "+billedAmount+" "+amountReceived);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBilledAmount() {
		return billedAmount;
	}

	public double getAmountReceived() {
		return amountReceived;
	}

	public double getOutstandingAmount() {
		return outstandingAmount;
	}

	public static void main(String[] args) {
		BillingEngine be = new BillingEngine();
		be.calculateBill();
	}

}
