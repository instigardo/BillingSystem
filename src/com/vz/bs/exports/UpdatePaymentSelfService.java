package com.vz.bs.exports;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.vz.bs.helper.SQLHelper;

@Path("/paymentselfservice")
public class UpdatePaymentSelfService {
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sendFinance(@QueryParam("accountNumber") String accountNumber,@QueryParam("amount") String amount,@QueryParam("paymentdate") String payday) {
		SQLHelper sql=new SQLHelper();
		String selfService="Payment Updated";
		sql.UPDATE("payments_summary", "amount_received='"+amount+"',"+"payment_date='"+payday+"'", "account_number="+accountNumber);
		return selfService;
	}
}