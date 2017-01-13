package com.abonado.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/v1/restdemo")
public class RestDemoService {
	
	@Path("/display/name")
    @GET
	public Response displayName(){
		ResponseBuilder builder = Response.status(Response.Status.OK);
		builder.entity("Rama Venna");
		return builder.build();
	}

}
