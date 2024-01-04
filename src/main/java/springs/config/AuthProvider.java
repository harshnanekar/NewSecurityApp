package springs.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import springs.repository.UserRepo;

@Component
public class AuthProvider implements AuthenticationProvider {
	
	@Autowired
	private UserRepo user;
	
	@Autowired
	public BCryptPasswordEncoder encoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		System.out.println("Personal auth provider");
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		List<Map<String,Object>> users = user.allemp(username);
		
		if(users.size() > 0) {
			String pass= (String) users.get(0).get("password");
			boolean val = encoder.matches(password, pass);
			
			long id = (long) users.get(0).get("id");
			
			if(val) {
				return new UsernamePasswordAuthenticationToken(username,pass,getAuthority(id));
			}else {
				throw new BadCredentialsException("Invalid Password");
			}
		}else {
			throw new BadCredentialsException("Invalid Username");
		}

	}
	
	

	private List<GrantedAuthority> getAuthority(long id) {
		//List<Map<String,Object>> auth = user.performAuthorize(id);
		List<Map<String,Object>> auth = user.roleauthorize(id);
		List<GrantedAuthority> li = new ArrayList<>();
		
		String role =(String) auth.get(0).get("roles");
		li.add(new SimpleGrantedAuthority(role));
		
//		for(int i =0 ;i < auth.size();i++) {
//			String authority = (String) auth.get(i).get("authority_name");
//			li.add(new SimpleGrantedAuthority(authority));
//		}
		return li;
	}



	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
