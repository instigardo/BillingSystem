package com.vz.bs.puller;

import java.io.StringReader;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.vz.bs.be.AccountParser;
import com.vz.bs.be.BillFormatter;
import com.vz.bs.be.BillingEngine;
import com.vz.bs.helper.SQLHelper;
import com.vz.bs.re.RatingEngine;

public class CustomerInfoExtractor {
	String infoArray[] = new String[19];


	public String[] getInfoArray() {
		return infoArray;
	}
	String accno;

	public String getAccno() {
		return accno;
	}

	public CustomerInfoExtractor() throws SQLException {
		SQLHelper sqlHelp = new SQLHelper();
		AccountParser ap = new AccountParser();
		if(ap.account().equals(null)){
			
		}else{
		String arr[] = ap.account();
		int tAccounts=ap.getTotalAccountNumber();
		System.out.println(arr.length);
		portfolioDateStore pds=new portfolioDateStore();
		String billingDate=pds.getBdate();
		String portfolio=pds.getPortfolio();
		sqlHelp.INSERT("bill_cycle_summary", "seq_1.nextval,'"+billingDate+"',"+tAccounts+",'"+portfolio+"',"+"'P'");

		if(tAccounts!=0)
		{
			for (int i = 0; i < arr.length; i++) {
		

			ProfileJsonGet jget = new ProfileJsonGet();
			jget.setAccno(arr[i]);
			accno=arr[i];
			System.out.println(accno);
			BillingEngine be = new BillingEngine();
			be.setAccountNumber(arr[i]);
			be.calculateBill();
			
			RatingEngine re = new RatingEngine();
			re.ImplementRatingEngine();
			String jsonString = jget.JsonGet();
			JsonReader reader = Json.createReader(new StringReader(jsonString));
			JsonObject json = reader.readObject();
			/* Customer Details */

			infoArray[0] = json.getJsonObject("customerdetails").getString(
					"fname")
					+ " "
					+ json.getJsonObject("customerdetails").getString("lname");
			infoArray[1] = json.getJsonObject("customerdetails").getString(
					"contactnumber");
			infoArray[2] = json.getJsonObject("customerdetails")
					.getJsonObject("connectionaddress").getString("streetname")
					+ ", \n"
					+ json.getJsonObject("customerdetails")
							.getJsonObject("connectionaddress")
							.getString("city")
					+ ", \n"
					+ json.getJsonObject("customerdetails")
							.getJsonObject("connectionaddress")
							.getString("state")
					+ ", \n"
					+ json.getJsonObject("customerdetails")
							.getJsonObject("connectionaddress")
							.getString("country")
					+ ", \n"
					+ json.getJsonObject("customerdetails")
							.getJsonObject("connectionaddress").get("zipcode");
			infoArray[3] = json.getJsonObject("customerdetails").getString(
					"customerid");
			infoArray[4] = json.getJsonObject("customerdetails").getString(
					"billstartdate");

			/* previous period */

			infoArray[5] = be.getBilledAmount() + "";
			infoArray[6] = be.getAmountReceived() + "";
			infoArray[7] = be.getOutstandingAmount() + "";

			/* Current Charges */

//additional			
infoArray[8] = re.getAdditionalCharges() + "";
//taxes			
infoArray[9] = re.getTaxAmount() + "";
//total			
infoArray[10] = re.getTotalAmount() + "";


			if (be.isNewCustomer()) {
				sqlHelp.EXEC("bill_details", infoArray[3] + ",'"+ jsonString + "'," + " null ,"+ Double.parseDouble(infoArray[10]) + ",to_date(sysdate,'DD/MON/YYYY'),null,null");
			} else {
				sqlHelp.EXEC("bill_details_update",  infoArray[3]+",'" + jsonString+ "'," + Double.parseDouble((infoArray[10])) + ", to_date(sysdate,'DD/MON/YYYY')");
			}
			
			BillFormatter billFormatter = new BillFormatter(infoArray);
			String billString = billFormatter.getGeneratedBill();
			System.out.println("here i am");
			/* previous bills and new bill insertion */
			String pBill="";
			String pBill1="";
			ResultSet rs=sqlHelp.SELECT("bill_cycle_details", "bill_generated,previous_bill_1" , "account_num="+infoArray[3]);
			while(rs.next()){
				pBill=rs.getString("bill_generated");
				pBill1=rs.getString("previous_bill_1");
			}
			sqlHelp.EXEC("bill_cycle_update1", infoArray[3]+",'"+billString+"',"+"'"+pBill+"',"+"'"+pBill1+"'");

			/* update bill amounts in payment_summary table */
			sqlHelp.UPDATE("payments_summary", "billed_amount="+infoArray[10]+",billing_date='"+billingDate+"',amount_received="+0, "account_number="+accno);
			
		}
		}
	ResultSet rs1=sqlHelp.SELECT("SYS.USER_SEQUENCES", "last_number", "sequence_name = 'SEQ_1'");
	int billId = 0;		
	while(rs1.next()){
			billId=rs1.getInt("last_number");
			}
		sqlHelp.UPDATE("bill_cycle_summary", "STATUS_P_C_F='C'", "bill_cycle_id="+billId);
		}
	}

	
	  public static void main(String[] args) throws SQLException { 
		  CustomerInfoExtractor cie=new CustomerInfoExtractor(); 
	//  BillingEngine be=new BillingEngine();
	//  be.toBillFormatter();
	  }
	 
}
