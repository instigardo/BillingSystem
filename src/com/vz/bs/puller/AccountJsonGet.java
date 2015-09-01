package com.vz.bs.puller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;

import com.vz.be.serv.SecServlet;

@Path("/accountgetter")
public class AccountJsonGet {

	String jsonString;

	@GET
	// @Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String JsonGet() {
		portfolioDateStore pds=new portfolioDateStore();
		String billingDate=pds.getBdate();//"23-JAN-2001";
		String portfolio=pds.getPortfolio();
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target("http://localhost:8080/OrderManagement")
				.path("rest").path("om").path("billingAccountPull").queryParam("billingDate", billingDate).queryParam("portfolio", portfolio);
		// getting String data
		jsonString = target.request(MediaType.TEXT_PLAIN).get(String.class);
		System.out.println(jsonString);
		
//		jsonString="{\"accountNo\":[\"501\",\"502\",\"503\"],\"totalNumOfAcc\":\"3\"}";	
		return jsonString;
	}

	public String getJsonString() {
		return jsonString;
	}
	public static void main(String[] args) {
		AccountJsonGet ac=new AccountJsonGet();
		System.out.println(ac.JsonGet());
	}
}
