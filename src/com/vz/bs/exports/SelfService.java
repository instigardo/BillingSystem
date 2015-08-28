package com.vz.bs.exports;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.vz.bs.be.BillingEngine;
import com.vz.bs.helper.SQLHelper;

@Path("/selfservice")
public class SelfService {
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sendFinance(@QueryParam("accountNumber") String accountNumber) {
		SQLHelper sql=new SQLHelper();
		String selfService;
	//	BillingEngine be = new BillingEngine();
	//	be.getBillString();
		int acno=Integer.parseInt(accountNumber);
		ResultSet rs=sql.SELECT("bill_cycle_details", "bill_generated,previous_bill_1,previous_bill_2", "account_num="+acno);
		try {
			if(rs.next()){
			do{
				selfService="{\"currentBill\":"+rs.getString("bill_generated")+",\"P1\":"+rs.getString("previous_bill_1")+",\"P2\":"+rs.getString("previous_bill_2")+"}";
				return selfService;
			}while(rs.next());
			}
			else
				return "No Bill Found";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "No Bill Found";
	}
}