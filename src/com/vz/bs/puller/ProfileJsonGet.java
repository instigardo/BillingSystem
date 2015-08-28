package com.vz.bs.puller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.client.ClientConfig;

@Path("/profilegetter")
public class ProfileJsonGet {
	String accno;

	public void setAccno(String accno) {
		System.out.println(accno);
		this.accno = accno;
	}

	public String getAccno() {
		return accno;
	}

	String jsonString;

	@GET
	// @Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String JsonGet() {
		accno="501";
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target("http://localhost:8080/OrderManagement")
				.path("rest").path("om").path("profile").queryParam("accno", accno);
		// getting String data
		jsonString = target.request(MediaType.TEXT_PLAIN).get(String.class);
		System.out.println(jsonString);
		return jsonString;
	}

	public String getJsonString() {
		return jsonString;
	}
}
