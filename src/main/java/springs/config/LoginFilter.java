package springs.config;

import java.io.IOException;



import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		
		String username = req.getHeader("username");
		
		if(!username.contains("@")) {
			System.out.println("Method executed" + username);
			 res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid email format");
	            return;		
	            
		}else {
			chain.doFilter(request, response);
		}
		
		System.out.println("Filter called");
		chain.doFilter(request, response);
	}

}
