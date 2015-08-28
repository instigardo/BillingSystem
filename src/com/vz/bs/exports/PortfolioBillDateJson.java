package com.vz.bs.exports;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import com.google.gson.GsonBuilder;

public class PortfolioBillDateJson
{

	public String sendOrderManagementJson(String portfolio,String billCycleDate)
	{
		
		JsonObjectBuilder orderManagementJson=Json.createObjectBuilder();
		orderManagementJson.add("Portfolio",portfolio);
		orderManagementJson.add("BillCycleDate",billCycleDate);
				
		return new GsonBuilder().setPrettyPrinting().create().toJson(orderManagementJson.build());
		
	
	}
		public static void main(String args[])
		{
			PortfolioBillDateJson pbdj=new PortfolioBillDateJson();
			System.out.println(pbdj.sendOrderManagementJson("C","20-AUG-2015"));
			
		}
}
