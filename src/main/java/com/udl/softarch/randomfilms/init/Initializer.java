package com.udl.softarch.randomfilms.init;

import javax.servlet.*;

import com.udl.softarch.randomfilms.config.RandomFilmsAppContext;
import com.udl.softarch.randomfilms.filters.SimpleCORSFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.EnumSet;

public class Initializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RandomFilmsAppContext.class);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
        FilterRegistration.Dynamic security = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
        security.addMappingForUrlPatterns(dispatcherTypes, true, "/*");

        FilterRegistration.Dynamic cors = servletContext.addFilter("simpleCORSFilter", new SimpleCORSFilter());
        cors.addMappingForUrlPatterns(dispatcherTypes, true, "/*");

        FilterRegistration.Dynamic httpMethods = servletContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter());
        httpMethods.addMappingForUrlPatterns(dispatcherTypes, true, "/*");


        servletContext.addListener(new ContextLoaderListener(rootContext));
    }

}
