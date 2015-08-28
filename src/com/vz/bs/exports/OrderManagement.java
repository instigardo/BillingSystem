package com.vz.bs.exports;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ordermanagement")
public class OrderManagement {
    @GET
   @Produces(MediaType.TEXT_HTML)
   public String sendToOrderManagement(){
       PortfolioBillDateJson request=new PortfolioBillDateJson();
	   return request.sendOrderManagementJson("C", "20-AUG-2015");
     
   }	
 

}