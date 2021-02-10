package com.exercise.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	
	 private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/static/" };

	 @Override
	  public void addCorsMappings(CorsRegistry registry) {
	    registry.addMapping("/").allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
	  }

	  @Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
	    registry.addResourceHandler("/documentation/**").addResourceLocations("classpath:/META-INF/resources/");
	  }
	  
	  @Override
	  public void addViewControllers(ViewControllerRegistry registry) {
			registry.addRedirectViewController("/documentation/v2/api-docs", "/v2/api-docs").setKeepQueryParams(true);
			registry.addRedirectViewController("/documentation/swagger-resources/configuration/ui","/swagger-resources/configuration/ui");
			registry.addRedirectViewController("/documentation/swagger-resources/configuration/security","/swagger-resources/configuration/security");
			registry.addRedirectViewController("/documentation/swagger-resources", "/swagger-resources");
		}

	  @Bean
	  public ViewResolver getViewResolver() {
	    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	    resolver.setSuffix(".html");
	    return resolver;
	  }

}
