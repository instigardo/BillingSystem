package com.vz.om.exports;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/billAccountPull")
public class AccountsJsonStringSender {
    @GET
   @Produces(MediaType.TEXT_PLAIN)
   public String sendProfile(@DefaultValue("null")@QueryParam("billingDate") String billDate,@DefaultValue("null")@QueryParam("portfolio") String portfolio){
    	String jsonString="no data found";
    	if(billDate.equals("25-jun-15") && portfolio.equals("C"))
		jsonString="{\"accountNo\":[\"501\",\"502\",\"503\"],\"totalNumOfAcc\":\"3\"}";		
		return jsonString;
     
   }	

/*   public static void main(String args[])
   {
	   AccountsJsonStringSender fr=new AccountsJsonStringSender();
	   System.out.println(fr.sendProfile());
	  
   }
*/}