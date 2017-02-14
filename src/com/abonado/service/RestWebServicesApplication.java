package com.abonado.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.abonado.webservice.RestDemoService;

@ApplicationPath("/rest-app/*")
public class RestWebServicesApplication extends Application{
	
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(RestDemoService.class);
		return classes;
		
	}

}
