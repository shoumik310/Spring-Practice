package com.spring.demo.webapp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class MainWebAppInitializer implements WebApplicationInitializer  {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		var context = new AnnotationConfigWebApplicationContext();
		context.register(WebConfig.class);
		
		// Generally while developping spring web applications two application contexts are used 

		/**
		 * The first is used with  ContextLoaderListener and is used to create the root context 
		 * and responsible for loading beans which are shared by multiple
		 * dispatcher servlet like beans related to the service layer and data access layer.
		 */
		servletContext.addListener(new ContextLoaderListener(context));

		/**
		 * The second is with dispatch servlet and is responsible for loading web component
		 * specific beans like controllers, view resolvers and handler mapping.
		 */
		ServletRegistration.Dynamic appServlet = servletContext.addServlet("mvc",
				new DispatcherServlet(new GenericWebApplicationContext()));
		appServlet.setLoadOnStartup(1);
		appServlet.addMapping("/");

	}

}
