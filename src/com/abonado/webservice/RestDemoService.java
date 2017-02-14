package com.abonado.webservice;

import java.io.File;
import java.util.Iterator;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import com.abonado.service.SecurityUtil;
import com.abonado.vo.DocumentUploadForm;

@Path("/v1/restdemo")
public class RestDemoService {
	
	/*
	 * Input: None
	 * Output: Return a name.
	 * url: GET: localhost:8080/demo-app/rest/v1/restdemo/display/name
	 */
	@Path("/display/name")
    @GET
	public Response displayName(){
		ResponseBuilder builder = Response.status(Response.Status.OK);
		builder.entity("Rama Venna");
		return builder.build();
	}
	
	@Path("/token")
	@POST
	public Response getToken(@FormParam("userName") String userName, @FormParam("password") String password){
		System.out.println("userName::::"+userName);
		System.out.println("password::::"+password);
		if(userName == null || password == null){
			return Response.status(Response.Status.UNAUTHORIZED).entity("User Name and Password must be provided").build();
		}
		String token = SecurityUtil.generateToken(userName, password);
		
		ResponseBuilder builder = getResponseBuilder(Response.Status.OK, "content-type","text/plain");
		builder.entity(token);
		return builder.build();
	}
	
	@Path("/formdemo")
	@POST
	public Response getFormData(@FormParam("name") String name, @FormParam("age") String age){
		System.out.println("name::::"+name);
		System.out.println("age::::"+age);
				
		ResponseBuilder builder = getResponseBuilder(Response.Status.OK, "content-type","text/plain");
		builder.entity("added user with name:"+name+" age:"+age);
		return builder.build();
	}
	
	
	
	@Path("/upload/document")
	@POST
	public Response uploadDocument(@MultipartForm DocumentUploadForm documentUploadForm, @HeaderParam("security_token") String securityToken){
		System.out.println("Service Invoked");
		String fileName = documentUploadForm.getFileName();
		byte[] fileContent = documentUploadForm.getFileContent();
		
		System.out.println(new String(fileContent));
		System.out.println("Security Token::::"+securityToken);
		System.out.println("File Size::::"+fileContent.length);
		
		return Response.status(Response.Status.OK).entity(fileName).build();
	}
	
	public ResponseBuilder getResponseBuilder(Status statusCode, String headerKey, String headerValue){
		ResponseBuilder builder = Response.status(statusCode);
		if(headerKey != null && headerValue != null){
			builder.header(headerKey, headerValue);
		}
		
		return builder;
	}
	
	@Path("/pathparam/welcome/{name: [a-zA-Z]+}")
	@GET
	public Response getNameByPathParam(@PathParam("name") String name){
		return Response.status(Response.Status.OK).entity("Welcome:"+name).build();
	}
	
	
	@Path("/queryparam/welcome")
	@GET
	public Response getNameByQueryParam(@NotNull @QueryParam("name") String name){
		return Response.status(Response.Status.OK).entity("Welcome:"+name).build();
	}
	
	@Path("/matrixparam/welcome")
	@GET
	public Response getNameByMatrixParam(@MatrixParam("name") String name, @MatrixParam("country") String country){
		return Response.status(Response.Status.OK).entity("Name:::"+name+" Country:::"+country).build();
	}
	
	@Path("/context/uriinfo/demo/{name}")
	@GET
	public Response getURIInfoValues(@Context UriInfo ui){
		MultivaluedMap<String, String> queryParameters = ui.getQueryParameters();
		MultivaluedMap<String, String> pathParameters = ui.getPathParameters();
		System.out.println(ui.getPath());
		System.out.println(ui.getAbsolutePath());
		System.out.println(ui.getBaseUri());
		System.out.println(ui.getRequestUri());
		
		System.out.println(pathParameters.get("name"));
		return Response.status(Response.Status.OK).entity("Query Param Map Size:"+queryParameters.size()
		                                                  +" Path Param Map Size:"+pathParameters.size()).build();
	}
	
	@Path("/context/headerinfo/demo")
	@GET
	public Response getHeaderValues(@Context HttpHeaders headers){
		MultivaluedMap<String, String> headersMap = headers.getRequestHeaders();
		Set<String> headersMapKeySet = headersMap.keySet();
		Iterator<String> headersMapKeySetItr= headersMapKeySet.iterator();
		
		while(headersMapKeySetItr.hasNext()){
			String key = headersMapKeySetItr.next();
			String value = headersMap.getFirst(key);
			
			System.out.println("Header Key::::"+key+" Header Value::::"+value);
		}
		
		return Response.status(Response.Status.OK).entity("Number Of Headers:::"+headersMap.size()).build();
	}
	
	@Path("/download/file")
	@GET
	@Produces("application/pdf")
	public Response getPDFFile(){
		
		File file = new File("D:\\Certification\\Webservices\\Java Web Services Up and Running, 2nd Edition.pdf");
		
		ResponseBuilder responseBuilder = Response.ok((Object)file);
		responseBuilder.header("Content-Disposition","attachment; filename=java-webservices-up-and-running");
		return responseBuilder.build();
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
