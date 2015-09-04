package com.vz.bs.puller;


public class portfolioDateStore {
	static String portfolio;
	static String Bdate;
	static int flag;
		public String getPortfolio() {
		return portfolio;
	}
	public String getBdate() {
		return Bdate;
	}
	public void store(String portfolio, String bdate) {
		this.portfolio = portfolio;
		Bdate = bdate;
	}
	public static int getFlag() {
		return flag;
	}
	public static void setFlag(int flag) {
		portfolioDateStore.flag = flag;
	}
	
	
}
