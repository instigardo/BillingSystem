package com.vz.bs.re;

import com.vz.be.serv.SecServlet;
import com.vz.bs.be.BillingEngine;
import com.vz.bs.be.ServicesNProductParser;
import com.vz.bs.puller.portfolioDateStore;

public class RatingEngine {
	String portfolio;
	ICMBBill cmbBill = new CMBBill();
	IWirelessBill wirelessBill = new WirelessBill();
	IVESBill vesBill = new VESBill();
	double usage;
	int billingPeriod;
	int daysInMonth;
	int productId=101;
	int stateCode=01;
	
	double discount;
	double amount;
	int minProducts;
	int maxProducts;
	
	double taxAmount;
	double recurringCharges;
	double nonRecurringCharges;
	double discountedCharges=0;
	double penalty=0;
	double totalAmount=0;
	double additionalCharges=0;
	double onlineBackupAndSharingCharges=0;
	public double getOnlineBackupAndSharingCharges() {
		return onlineBackupAndSharingCharges;
	}

	public void setOnlineBackupAndSharingCharges(
			double onlineBackupAndSharingCharges) {
		this.onlineBackupAndSharingCharges = onlineBackupAndSharingCharges;
	}

	public double getSetTopBoxRent() {
		return setTopBoxRent;
	}

	public void setSetTopBoxRent(double setTopBoxRent) {
		this.setTopBoxRent = setTopBoxRent;
	}

	public double getDVRRent() {
		return DVRRent;
	}

	public void setDVRRent(double dVRRent) {
		DVRRent = dVRRent;
	}

	public double getVat() {
		return vat;
	}

	public void setVat(double vat) {
		this.vat = vat;
	}

	double setTopBoxRent=0;
	double DVRRent=0;
	double vat=0;
	
	
	 public double getAdditionalCharges() {
		return additionalCharges;
	}

	public void setAdditionalCharges(double additionalCharges) {
		this.additionalCharges = additionalCharges;
	}

	public void ImplementRatingEngine() {
		BillingEngine be=new BillingEngine();
		ServicesNProductParser snp=new ServicesNProductParser();
		snp.Services();
		snp.AdditionalServices();
		portfolioDateStore pds=new portfolioDateStore();
			portfolio=pds.getPortfolio();
				//portfolio="cmb";
		System.out.println(portfolio);
		if(portfolio.equals("cmb")){
			taxAmount=cmbBill.calculateTaxRates(productId, stateCode);
			if(be.isNewCustomer())
			nonRecurringCharges=cmbBill.calculateNonRecurringCharges();
			
		}else if(portfolio.equals("vzw")){
			taxAmount=wirelessBill.calculateTaxRates(productId, stateCode);
			if(be.isNewCustomer())
			nonRecurringCharges=wirelessBill.calculateNonRecurringCharges();
			
		}else if(portfolio.equals("ves")){
			taxAmount=vesBill.calculateTaxRates(productId, stateCode);
			if(be.isNewCustomer())
			nonRecurringCharges=vesBill.calculateNonRecurringCharges();
			
		}
		else{}
		
		additionalCharges=snp.getProductsSubTotal()+snp.getVesPenalty()+snp.getVesVAT()-snp.getVesDiscount();
		totalAmount=taxAmount+nonRecurringCharges+snp.getProductsSubTotal()+snp.getServicesSubTotal()+snp.getVesPenalty()+snp.getVesVAT()-snp.getVesDiscount();
			
		
	}

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public double getRecurringCharges() {
		return recurringCharges;
	}

	public void setRecurringCharges(double recurringCharges) {
		this.recurringCharges = recurringCharges;
	}

	public double getNonRecurringCharges() {
		return nonRecurringCharges;
	}

	public void setNonRecurringCharges(double nonRecurringCharges) {
		this.nonRecurringCharges = nonRecurringCharges;
	}

	public double getDiscountedCharges() {
		return discountedCharges;
	}

	public void setDiscountedCharges(double discountedCharges) {
		this.discountedCharges = discountedCharges;
	}

	public double getPenalty() {
		return penalty;
	}

	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getUsage() {
		return usage;
	}

	public void setUsage(double usage) {
		this.usage = usage;
	}

	public int getBillingPeriod() {
		return billingPeriod;
	}

	public void setBillingPeriod(int billingPeriod) {
		this.billingPeriod = billingPeriod;
	}

	public int getDaysInMonth() {
		return daysInMonth;
	}

	public void setDaysInMonth(int daysInMonth) {
		this.daysInMonth = daysInMonth;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getStateCode() {
		return stateCode;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}

	public String getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}






	public static void main(String[] args) {
		RatingEngine re = new RatingEngine();
		re.ImplementRatingEngine();
//		RatingEngine.PullProfile PP = re.new PullProfile();
//		PP.puller();
	}
}