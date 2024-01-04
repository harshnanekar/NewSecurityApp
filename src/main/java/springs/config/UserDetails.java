package springs.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import springs.repository.UserRepo;

@Service
public class UserDetails implements UserDetailsService{
	
	@Autowired
	private UserRepo repo;

	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		List<Map<String,Object>> user = repo.allemp(username);
		List<GrantedAuthority> authority = new ArrayList<>();
		String users;
		String pass;
		
		if(user.size() == 0) {
			throw new UsernameNotFoundException("Username Not found");
		}else {
			users = (String) user.get(0).get("email");
			pass = (String) user.get(0).get("password");
			String auth = (String) user.get(0).get("roles");
			System.out.println("user and pass " + users + pass + auth);
			authority.add(new SimpleGrantedAuthority(auth));
		}
		
		return new User(users,pass,authority);
	}

}
