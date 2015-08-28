package com.vz.om.exports;

import javax.ws.rs.DefaultValue;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/costSender")
public class CostJsonStringSender {
    @GET
   @Produces(MediaType.TEXT_PLAIN)
   public double sendProfile(@DefaultValue("null")@QueryParam("id") String id){
    	double cost=0;
    	
    	if(id.equals("b1001"))
		cost=0.22;		
		return cost;
     
   }	

/*   public static void main(String args[])
   {
	   AccountsJsonStringSender fr=new AccountsJsonStringSender();
	   System.out.println(fr.sendProfile());
	  
   }
*/}