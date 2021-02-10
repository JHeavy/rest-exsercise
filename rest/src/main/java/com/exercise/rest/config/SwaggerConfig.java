package com.exercise.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author jvz19
 * @version 1.0
 * <p>No security is required</p>
 * */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Autowired
	SwaggerPropsConfig swaggerPropsConfig;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.exercise.rest.controller"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo())
				.useDefaultResponseMessages(false);
	}
	
	ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title(swaggerPropsConfig.getTitle())
            .description(swaggerPropsConfig.getDescription())
            .license(swaggerPropsConfig.getLicense())
            .licenseUrl(swaggerPropsConfig.getLicenseUrl())
            .version(swaggerPropsConfig.getVersion())
            .build();
    } 
}
