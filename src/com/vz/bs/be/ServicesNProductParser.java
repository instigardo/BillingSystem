package com.vz.bs.be;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

import com.vz.bs.puller.CostJsonGet;
import com.vz.bs.puller.ProfileJsonGet;

public class ServicesNProductParser {
	double servicesSubTotal=0;
	double productsSubTotal=0;
	double vesVAT;
	double vesDiscount;
	double vesPenalty;
	double servicePriceArray[]=new double[100];
	
	ProfileJsonGet profileJsonGet = new ProfileJsonGet();
	public double getServicesSubTotal() {
		return servicesSubTotal;
	}
	public double getProductsSubTotal() {
		return productsSubTotal;
	}
	public JsonArray Services() {
		JsonArray servicesArray;
		JsonArrayBuilder servicesArray1=Json.createArrayBuilder();
		int arrayLength;
		String serviceName;
		String serviceId;
		double servicePrice;

		String costS;
		CostJsonGet cjGet=new CostJsonGet();
		String servicesJson = profileJsonGet.JsonGet();
		//System.out.println(servicesJson);
		JsonReader reader = Json.createReader(new StringReader(servicesJson));
		JsonObject json = reader.readObject();
		servicesArray = json.getJsonObject("existingsnp").getJsonArray("services");
		if(servicesArray.equals(null))
		{
			JsonObjectBuilder jobj=Json.createObjectBuilder(); 
			jobj.add("name","no Service");
			jobj.add("price",0);
			JsonObject jo=jobj.build();
			servicesSubTotal=0;
			servicesArray1.add(jo).build();
			return servicesArray;
		}else
		{
		System.out.println(servicesArray.toString());
		arrayLength = servicesArray.toArray().length;
		System.out.println(arrayLength);
		for (int i = 0; i < arrayLength; i++) {
			serviceName = servicesArray.getJsonObject(i).getString("servicename");
			serviceId = servicesArray.getJsonObject(i).getString("servicecode");
			System.out.println(serviceName+" "+serviceId);
			cjGet.setId(serviceId);
			costS=cjGet.costGet();
			servicePrice=Double.parseDouble(costS);
			System.out.println(servicePrice);
			System.out.println(servicePriceArray[i]);
			servicePriceArray[i]=servicePrice;
			JsonObjectBuilder jobj=Json.createObjectBuilder(); 
			jobj.add("name",serviceName);
			jobj.add("price",servicePrice);
			JsonObject jo=jobj.build();
			System.out.println(jo.toString());
			servicesSubTotal=servicesSubTotal+servicePrice;
			servicesArray1.add(jo);
		}
		//servicesArray1.build();
		servicesArray=servicesArray1.build();
		
		return servicesArray;
	}
	}
	public JsonArray AdditionalServices() {
		String portfolio;
		JsonArray productsArray;
		JsonArray vesArray;
		
		JsonArrayBuilder productsArray1=Json.createArrayBuilder();
		int arrayLength;
		String productsName;
		String productsId;
		double productsPrice;
		String costS;
		
		CostJsonGet cjGet=new CostJsonGet();
		String productsJson = profileJsonGet.JsonGet();
		JsonReader reader = Json.createReader(new StringReader(productsJson));
		JsonObject json = reader.readObject();
		portfolio=json.getString("lineofbusiness");
		productsArray = json.getJsonObject("existingsnp").getJsonArray("products");
		if(productsArray.equals(null))
		{
			JsonObjectBuilder jobj=Json.createObjectBuilder(); 
			jobj.add("name","no Service");
			jobj.add("price",0);
			JsonObject jo=jobj.build();
			productsSubTotal=0;
			productsArray1.add(jo).build();
			return productsArray;
		}else
		{
		arrayLength = productsArray.toArray().length;
		for (int i = 0; i < arrayLength; i++) {
			productsName = productsArray.getJsonObject(i).getString("productname");
			productsId = productsArray.getJsonObject(i).getString("productcode");
			cjGet.setId(productsId);
			costS=cjGet.costGet();
			productsPrice=Double.parseDouble(costS);
			JsonObjectBuilder jobj=Json.createObjectBuilder(); 
			jobj.add("name",productsName);
			jobj.add("price",productsPrice);
			JsonObject jo=jobj.build();
			productsSubTotal=productsSubTotal+productsPrice;
			productsArray1.add(jo);
		}
			if(portfolio.equals("ves")){
				vesVAT=0;
				vesDiscount=0;
				vesPenalty=0;
				String classOfService;
				int current;
				int max;
				int discount;
				double vat = 0;
				double discountAmt=0;
				double penalty = 0;
				vesArray=json.getJsonArray("contractdetails");
				arrayLength = vesArray.toArray().length;
				for (int i = 0; i < arrayLength; i++) {
					classOfService = vesArray.getJsonObject(i).getString("classofservice");
					current = vesArray.getJsonObject(i).getInt("current");
					max = vesArray.getJsonObject(i).getInt("max");
					discount = vesArray.getJsonObject(i).getInt("discount");
					/* for calculating vat */
					if(classOfService.equals("platinum"))
						vat=servicePriceArray[i]*0.15;
					else if(classOfService.equals("gold"))
						vat=servicePriceArray[i]*0.10;
					else if(classOfService.equals("silver"))
						vat=servicePriceArray[i]*0.05;
					else if(classOfService.equals("bronze"))
						vat=servicePriceArray[i]*0.025;
					else if(classOfService.equals("normal"))
						vat=servicePriceArray[i]*0;

					vesVAT=vesVAT+vat;
					/* for calculating discount */
					discountAmt=(servicePriceArray[i]*discount)/100;
					vesDiscount=vesDiscount+discountAmt;
					/* for calculating penalty */
					if(current<max)
					penalty=2*servicePriceArray[i]*(max-current);
					vesPenalty=vesPenalty+penalty;
				}
				JsonObjectBuilder jobj1=Json.createObjectBuilder(); 
					jobj1.add("name","Total VAT");
					jobj1.add("price",vesVAT);
					JsonObject jo1=jobj1.build();
					productsArray1.add(jo1);
					JsonObjectBuilder jobj2=Json.createObjectBuilder(); 
					jobj2.add("name","Total Discount");
					jobj2.add("price",vesDiscount);
					JsonObject jo2=jobj2.build();
					productsArray1.add(jo2);
					JsonObjectBuilder jobj3=Json.createObjectBuilder(); 
					jobj3.add("name","Total Penalty");
					jobj3.add("price",vesPenalty);
					JsonObject jo3=jobj3.build();
					productsArray1.add(jo3);
				}

		productsArray=productsArray1.build();
		return productsArray;
		}
	}


	  public double getVesVAT() {
		return vesVAT;
	}
	public double getVesDiscount() {
		return vesDiscount;
	}
	public double getVesPenalty() {
		return vesPenalty;
	}
	public static void main(String[] args) { 
		  ServicesNProductParser ap=new  ServicesNProductParser(); 
		  System.out.println(ap.Services().toString());
		  System.out.println(ap.AdditionalServices().toString());

		  }
	 
}
