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
import com.vz.bs.puller.portfolioDateStore;

public class PaymentProcessor {
	Connection con;
	Statement st;
	String dataForFinance;



	public void sendToFinance() {
		ConnectionGetter cg=new ConnectionGetter();
		con=cg.getConnection();
		portfolioDateStore pds=new portfolioDateStore();
				String bdate=pds.getBdate();
		try {
			
			st=con.createStatement();
			ResultSet rs = st
					.executeQuery("Select i.*,j.outstanding_amount from PAYMENTS_SUMMARY i, payments_outstanding_summary j where  i.account_number=j.account_number ORDER BY i.account_number");
			JsonObjectBuilder accountInfo=Json.createObjectBuilder();
			JsonArrayBuilder paymentArray=Json.createArrayBuilder();
			JsonObjectBuilder paymentDB=Json.createObjectBuilder();

			while (rs.next()) {
				int accountNumber=Integer.parseInt(rs.getString("account_number"));
				//System.out.println("here");
				double billedAmount=Double.parseDouble(rs.getString("billed_amount"));
				double amountReceived=Double.parseDouble(rs.getString("amount_received"));
				double outstandingAmount=Double.parseDouble(rs.getString("outstanding_amount"));
				Date paymentDate=rs.getDate("payment_date");
				String paymentDateString;
				if(rs.wasNull())
					paymentDateString="Not Paid";
				else
					paymentDateString=new SimpleDateFormat("dd/MMM/yyyy").format(paymentDate);
				Date billCycleDate=rs.getDate("billing_date");
				String billCycleDateString=new SimpleDateFormat("dd/MMM/yyyy").format(billCycleDate);

				accountInfo.add("accountNumber", accountNumber);
				accountInfo.add("billedAmount", billedAmount);
				accountInfo.add("amountReceived", amountReceived);
				accountInfo.add("paymentDate", paymentDateString);
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
