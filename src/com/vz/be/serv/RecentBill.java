package com.vz.be.serv;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
 

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

 
@WebServlet("/RecentBill")
public class RecentBill extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost( request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String portfolio = request.getParameter("portfolio");
		 
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
 
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
 
        Gson gson = new Gson(); 
        JsonObject myObj = new JsonObject();
 
        RecentBillInfo billInfo=getInfo(portfolio,out);
      //  Country countryInfo = getInfo(countryCode);
        JsonElement billObj = gson.toJsonTree(billInfo);
        if(billInfo.getbill_cycle_date() == null){
            myObj.addProperty("success", false);
        }
        else {
            myObj.addProperty("success", true);
        }
       //  myObj.add("billInfo", billObj);
     //  out.println(myObj.toString());
        //System.out.println(myObj.toString());
 
        out.close();
 		 
	}
	
	//Get Country Information
    private RecentBillInfo getInfo(String portfolio,PrintWriter out) {
 
    	RecentBillInfo bill=new RecentBillInfo();
       // Country country = new Country();
        Connection conn = null;            
        PreparedStatement stmt = null;     
        String sql = null;
 
        try {      
        	Class.forName("oracle.jdbc.driver.OracleDriver");
        	conn=DriverManager.getConnection(
        			"jdbc:oracle:thin:@localhost:1521:xe",
        			"hr","password");
        	String port = null;
        	if(portfolio.equals("ves"))
        		port="ves";
        	if(portfolio.equals("cmb"))
        		port="cmb";
        	if(portfolio.equals("vzw"))
        		port="vzw";
        	 sql= " select * from bill_cycle_summary where  portfolio=?"; 
            //sql = "Select * from COUNTRY where code = ?"; 
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, port.trim());
            
            ResultSet rs = stmt.executeQuery(); 
            out.println("<html><head><link rel=\"stylesheet\" href=\"bootstrap.css\" /></head><body><table id=\"displaytable\" class=\"table\"><tr><th>Bill Cycle Date</th><th>Total number of bills</th></tr><tr>");
            while(rs.next()){ 
            	
            	//out.println("hello");
               bill.setbill_cycle_date(rs.getDate("bill_cycle_date"));
                bill.settotal_num_accnts(rs.getInt("total_num_accnts"));
                
			    out.println(" <tr><td>"+bill.bill_cycle_date+"</td>");
			   
			    out.println(" <td>"+bill.total_num_accnts+"</td></tr>");
                
            }                                                                         
            out.println("</table>");
            rs.close();                                                               
            stmt.close();                                                             
            stmt = null;                                                              
            conn.close();                                                             
            conn = null;                                                   
 
        }                                                               
        catch(Exception e){System.out.println(e);}                      
                
 
        return bill;
 
    } 
}
