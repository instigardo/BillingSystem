package com.vz.bs.puller;


public class portfolioDateStore {
	static String portfolio;
	static String Bdate;
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
	
}
