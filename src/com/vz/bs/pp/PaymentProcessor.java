package com.vz.bs.pp;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import java.sql.*;

import com.google.gson.GsonBuilder;
import com.vz.bs.helper.ConnectionGetter;

public class PaymentProcessor {
	Connection con;
	Statement st;
	String dataForFinance;



	public void sendToFinance() {
		ConnectionGetter cg=new ConnectionGetter();
		con=cg.getConnection();
		try {
			st=con.createStatement();
			ResultSet rs = st
					.executeQuery("Select i.*,j.outstanding_amount from PAYMENTS_SUMMARY i, payments_outstanding_summary j where BILL_CYCLE_DATE='25-JUN-15' and i.account_number=j.account_number ORDER BY i.account_number");
			JsonObjectBuilder accountInfo=Json.createObjectBuilder();
			JsonArrayBuilder paymentArray=Json.createArrayBuilder();
			JsonObjectBuilder paymentDB=Json.createObjectBuilder();

			while (rs.next()) {
				int accountNumber=rs.getInt("account_number");
				System.out.println("here");
				int billedAmount=rs.getInt("billed_amount");
				int amountReceived=rs.getInt("amount_received");
				//int outstandingAmount=rs.getInt("outstanding_amount");
//				Date paymentDate=rs.getDate("payment_date");
				String paymentDateString;
			/*	if(rs.wasNull())
					paymentDateString="Not Paid";
				else
					paymentDateString=new SimpleDateFormat("dd/MON/yy").format(paymentDate);
*/				Date billCycleDate=rs.getDate("bill_cycle_date");
				String billCycleDateString=new SimpleDateFormat("dd/MMM/yyyy").format(billCycleDate);

				accountInfo.add("accountNumber", accountNumber);
				accountInfo.add("billedAmount", billedAmount);
				accountInfo.add("amountReceived", amountReceived);
//				accountInfo.add("paymentDate", paymentDateString);
				accountInfo.add("billCycleDate", billCycleDateString);
				
				paymentArray.add(accountInfo);
		
			}
			paymentDB.add("payments", paymentArray);
			String jsonObj=new GsonBuilder().setPrettyPrinting().create().toJson(paymentDB.build());
			
			dataForFinance= jsonObj;
			System.out.println(dataForFinance);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getDataForFinance() {
		return dataForFinance;
	}
	
	public static void main(String[] args) {
		PaymentProcessor pp=new PaymentProcessor();
		pp.sendToFinance();
		System.out.println(pp.getDataForFinance());
	}
}
