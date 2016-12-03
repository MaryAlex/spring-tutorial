package com.mine.config.Initializer;

import com.mine.config.RootConfig;
import com.mine.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// DispatcherServlet - main part of Spring MVC project. All requests goes through it.
// Also it's mapping controllers with views. (Briefly)
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Identifies one or more paths that DispatcherServlet will be mapped to. "/" means that it
    // will be the application’s default servlet. It will handle all requests coming into the
    // application.
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    // It will be used to configure the application context created by ContextLoaderListener
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {RootConfig.class};
    }

    // Define beans for DispatcherServlet’s application context.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {WebConfig.class};
    }
}
