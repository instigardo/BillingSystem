package com.vz.be.serv;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vz.bs.be.AccountParser;
import com.vz.bs.be.BillingEngine;
import com.vz.bs.helper.SQLHelper;
import com.vz.bs.puller.CustomerInfoExtractor;
import com.vz.bs.puller.portfolioDateStore;
import com.vz.bs.re.RatingEngine;
/*import javax.xml.rpc.ServiceException;

import com.service.BillServ;
import com.service.BillServServiceLocator;
*/
/**
 * Servlet implementation class SecServlet
 */
@WebServlet("/SecServlet")
public class SecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SecServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date d1;
		String portfolio=request.getParameter("portfolio");
		String date1=request.getParameter("date");	
		portfolioDateStore pds=new portfolioDateStore();
		pds.store(portfolio, date1);
		//date1=d1.toString();
	//	SQLHelper sqlHelp=new SQLHelper();
	/*	AccountParser aparser=new AccountParser();
		aparser.account();
		int accno=aparser.getTotalAccountNumber();
		System.out.println(aparser.getTotalAccountNumber());*/

		

		JsonObject value=Json.createObjectBuilder().add("portfolio",portfolio).add("date",date1).build();
		String result=value.toString();
		System.out.println(result);
		
		try {
			CustomerInfoExtractor cie=new CustomerInfoExtractor();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println("dsda");
	/*	String sql= 1011+",to_date('"+ date1 + "','DD/MON/YYYY')," + accno + ",'" + portfolio + "',"+"C";
		sqlHelp.INSERT("bill_cycle_summary",sql);*/
	


		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();		
		session.setAttribute("Success", "Bills Generated Successfully"+date1);
		System.out.println("success!!!!!");
		response.sendRedirect("index.jsp");
			
	}

}
