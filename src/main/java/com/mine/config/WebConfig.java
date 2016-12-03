package com.mine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.mine.config") // There must be your package with all java classes.
@Import({HibernateConfig.class})
public class WebConfig extends WebMvcConfigurerAdapter {
    // It is where to take pages. For example "home" will be taken from prefix+"home"+suffix.
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        // Set whether to make all Spring beans in the application context accessible as request attributes,
        // through lazy checking once an attribute gets accessed. This will make all such beans accessible in
        // plain ${...} expressions in a JSP 2.0 page, as well as in JSTL's c:out value expressions.
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }


    // Add a resource handler for serving static resources based on the specified URL path patterns.
    // The handler will be invoked for every incoming request that matches to one of the specified path patterns.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    // It's don't need there because of method above, but I write it for example.
    // It's must be if you don't override addResourceHandlers to say DispatcherServlet to serve the resources.
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
