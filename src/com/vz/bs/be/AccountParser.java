package com.vz.bs.be;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.http.HttpServletResponse;

import com.vz.bs.puller.AccountJsonGet;




public class AccountParser {
	int totalAccountNumber;
	
	public int getTotalAccountNumber() {
		return totalAccountNumber;
	}

	public String[] account() {
		Object[] accountArray;
		AccountJsonGet accountJsonGet = new AccountJsonGet();
		String accountJson = accountJsonGet.JsonGet();
		if(accountJson.toLowerCase().equals("null"))
		{

			return null;
		}else{
		JsonReader reader=Json.createReader(new StringReader(accountJson));
		JsonObject json=reader.readObject();
		totalAccountNumber=Integer.parseInt(json.getString("totalNumOfAcc"));
		System.out.println("accno:"+totalAccountNumber);
		accountArray=json.getJsonArray("accountNo").toArray();
		String[] accounts =new String[accountArray.length];
		for (int i = 0; i < accountArray.length; i++) {
			accounts[i]=accountArray[i].toString();
			accounts[i]=accounts[i].replace('"', ' ').trim();
		}
		
		return accounts;
		}
	}
	
	public static void main(String[] args) {
		AccountParser ap=new AccountParser();
		String arr[]=ap.account();
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
}
