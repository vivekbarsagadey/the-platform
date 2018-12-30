/**
 * 
 */
package com.platform.health.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author Sandeep
 *
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCORSFilter extends OncePerRequestFilter{
	private final List<String> allowedOrigins = Arrays.asList("http://localhost:4200","http://192.168.100.22:4321"); 
    public SimpleCORSFilter() {
    }

    @Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
    	/*HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) request;*/
    	String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", allowedOrigins.contains(origin) ? origin : "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Request-Headers", "access-control-allow-credentials,access-control-allow-origin,authorization");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, x-auth-token, origin, content-type, accept");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
        	filterChain.doFilter(request, response);
        }
		
	}
    /*@Override
    public void doFilterInternal(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        
    }*/

    /*@Override
    public void init(FilterConfig filterConfig) {
    	System.out.println("Initializing filter :{}"+ this);
    }*/

    @Override
    public void destroy() {
    	System.out.println("Destructing filter :{}"+ this);
    }

}
