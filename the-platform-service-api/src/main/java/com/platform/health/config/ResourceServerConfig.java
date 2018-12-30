package com.platform.health.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration

@EnableGlobalMethodSecurity(securedEnabled = true)
@Order(-2)
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {

		final String[] SWAGGER_UI = { "/swagger-resources/**", "/swagger-ui.html", "/v2/api-docs", "/webjars/**" };

		web.ignoring().antMatchers("/api/**").antMatchers(SWAGGER_UI);

	}
	@Bean
	public FilterRegistrationBean<SimpleCORSFilter> filterRegistration() {
		FilterRegistrationBean<SimpleCORSFilter> registrationBean = new FilterRegistrationBean<SimpleCORSFilter>();
		registrationBean.setFilter(new SimpleCORSFilter());
		// registrationBean.addUrlPatterns("/*");
		Collection<String> urlPatters = new ArrayList<String>();
		urlPatters.add("/api/*");
		urlPatters.add("/*");
		registrationBean.setUrlPatterns(urlPatters);
		registrationBean.setName("AllowCORS");
		registrationBean.setOrder(1);

		return registrationBean;
	}

}
