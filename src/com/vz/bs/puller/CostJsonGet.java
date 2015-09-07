package com.vz.bs.puller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.client.ClientConfig;

@Path("/costgetter")
public class CostJsonGet {
	String id;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	String jsonString;

	@GET
	// @Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String costGet() {
		//id="b1001";
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target("http://localhost:8080/PCatServiceCatalogue")
				.path("api").path("CostSender").queryParam("id", id);
		// getting String data
		jsonString = target.request(MediaType.TEXT_PLAIN).get(String.class);
		System.out.println(jsonString);
		return jsonString;
	}

	public String getJsonString() {
		return jsonString;
	}
}
