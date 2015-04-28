package com.udl.softarch.randomfilms.init;

import javax.servlet.*;

import com.udl.softarch.randomfilms.config.RandomFilmsAppContext;
import com.udl.softarch.randomfilms.filters.SimpleCORSFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class Initializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RandomFilmsAppContext.class);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        servletContext.addFilter("CORSFilter", new SimpleCORSFilter()).addMappingForUrlPatterns(null, false, "/*");
        servletContext.addFilter("httpMethodFilter", new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null, false, "/*");
        servletContext.addListener(new ContextLoaderListener(rootContext));
    }

}
