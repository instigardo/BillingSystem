package com.vz.bs.exports;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.vz.bs.pp.PaymentProcessor;

@Path("/finance")
public class FinanceReporting {
    @GET
   @Produces(MediaType.TEXT_HTML)
   public String sendFinance(){
    
    	PaymentProcessor pp=new PaymentProcessor();
    	pp.sendToFinance();
		return pp.getDataForFinance();
     
   }	

    public static void main(String args[])
   {
	   FinanceReporting fr=new FinanceReporting();
	   System.out.println(fr.sendFinance());
	  
   }
}