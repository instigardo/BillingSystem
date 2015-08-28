package com.vz.be.serv;
import java.sql.Date;



public class RecentBillInfo {
	Date bill_cycle_date;
	int total_num_accnts;
   
	public Date getbill_cycle_date() {
		return bill_cycle_date;
	}
	public void setbill_cycle_date(Date d) {
		this.bill_cycle_date=d;
	}
	public int gettotal_num_accnts() {
		return total_num_accnts;
	}
	public void settotal_num_accnts(int n) {
		this.total_num_accnts=n;
	}
	
}
