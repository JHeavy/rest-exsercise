package com.exercise.rest.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;

/**
 * @author jvz19
 * @version 1.0
 * <p>No security is required filter do nothing</p>
 * */
@Configuration
@EnableWebSecurity
public class SecConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity security) throws Exception {
		security.httpBasic().disable().authorizeRequests().antMatchers("/**").permitAll().and()
				.addFilterAfter(new SecFilter(), BasicAuthenticationFilter.class).csrf().disable()
				.headers().disable();
	}
	
	static final class SecFilter extends GenericFilterBean {

		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {
			chain.doFilter(request, response);
		}

	}
}
