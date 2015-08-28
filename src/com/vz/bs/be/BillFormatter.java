package com.vz.bs.be;

import javax.json.JsonObject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class BillFormatter 
{
	String generatedBill;
		public BillFormatter(String billData[])
		{
			JsonObjectBuilder Bill_Details=Json.createObjectBuilder();
			Bill_Details.add("Name",billData[0]);
			Bill_Details.add("PrimaryPhone",billData[1]);
			Bill_Details.add("Address", billData[2]);
			Bill_Details.add("AccountNumber", billData[3]);
			Bill_Details.add("BillDate", billData[4]);
			
			JsonObjectBuilder Account_Summary=Json.createObjectBuilder();
			
			JsonObjectBuilder Previous_Period=Json.createObjectBuilder();
			Previous_Period.add("PreviousBalance", billData[5]);
			Previous_Period.add("PaymentReceived", billData[6]);
			Previous_Period.add("BalanceForward", billData[7]);
	
		JsonObjectBuilder Services=Json.createObjectBuilder();
		
		ServicesNProductParser sp=new ServicesNProductParser();
		Services.add("Services",sp.Services());
		Services.add("Subtotal",sp.getServicesSubTotal());

JsonObjectBuilder Additional_Charges=Json.createObjectBuilder();

Additional_Charges.add("Additional_Services",sp.AdditionalServices());
Additional_Charges.add("Subtotal",billData[8]);

Account_Summary.add("Previous_Period",Previous_Period);
Account_Summary.add("Current_Charges",Services);
Account_Summary.add("Additional_Charges",Additional_Charges);

			
			Bill_Details.add("Account_Summary",Account_Summary);
			Bill_Details.add("Taxes", billData[9]);
			
			Bill_Details.add("Total_Amount" , billData[10]);
			
			
			JsonObject jo;
			jo=Additional_Charges.build();
			jo=Services.build();
			jo=Previous_Period.build();
			jo=Account_Summary.build();

			jo=Bill_Details.build();
			
			generatedBill=jo.toString();
			System.out.println(generatedBill);
	}
		
		public String getGeneratedBill()
		{
			return generatedBill;
		}
	public static void main(String[] args) {
		String billDat[]=new String[13];
		for (int i = 0; i < 11; i++) {
		billDat[i]="daVinchi";
		}
			BillFormatter bf=new BillFormatter(billDat);
	}
}
		