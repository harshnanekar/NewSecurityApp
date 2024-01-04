package springs.config;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;


@Configuration
public class Config{
	
//	@Bean
//	public InMemoryUserDetailsManager userdetails() {
//		
//		UserDetails user= User.withDefaultPasswordEncoder().username("harsh").password("12345")
//				.authorities("admin").build();
//		
//		return new InMemoryUserDetailsManager(user);
//		
//	}
	
//	@Bean
//	public InMemoryUserDetailsManager manage() {
//		
//		UserDetails user= User.withUsername("admin").password("admin").authorities("admin").build();
//		
//		
//		return new InMemoryUserDetailsManager(user);
//	}

	
//	
	@Bean
	public BCryptPasswordEncoder pass() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain security(HttpSecurity http) throws Exception{
		
		http.
		 csrf(Customizer.withDefaults()).authorizeHttpRequests(authorize -> {
				try {
					authorize
//					
					.requestMatchers("/User/info").hasRole("ADMIN")
       				.requestMatchers("/User/contact").hasAnyRole("USER")
					.requestMatchers("/User/home").
					authenticated().and()
					.formLogin(log -> log.loginPage("/User/loginPage").failureUrl("/User/loginPage")
					.defaultSuccessUrl("/User/home"))
					.addFilterBefore(new LoginFilter(),BasicAuthenticationFilter.class)
					.httpBasic(Customizer.withDefaults());
				} catch (Exception e) {
					e.printStackTrace();
				}
				});
		
		 
		 http.csrf().disable().authorizeHttpRequests(nauth -> {
			nauth.requestMatchers("/User/loginPage").permitAll();
		 });

//		
//	          http.authorizeHttpRequests(request -> {
//	        	try {
//					request.antMatchers("/User/loginPage","/User/home").denyAll()
//					.and().formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//	          
//	        	  });
	          
//		
//		http.authorizeHttpRequests(request -> {
//			 try {
//				request.requestMatchers("/User/home","/User/login").denyAll().and()
//				 .formLogin(Customizer.withDefaults());
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});
//		
//		http.authorizeHttpRequests(request -> {
//			try {
//				request.requestMatchers("/User/loginPage","/User/home").permitAll().and().
//				httpBasic(Customizer.withDefaults());
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});
		
		
		return http.build();
	}


}
