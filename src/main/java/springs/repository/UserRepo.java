package springs.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepo {

	@Autowired
	private JdbcTemplate template;
	
	
	public List<Map<String,Object>> allemp(String username){
		String sql= "select * from employees where email=?";
		return template.queryForList(sql,username);
	}


	public List<Map<String, Object>> performAuthorize(long id) {
		String sql= "select * from employees e inner join user_authority u on e.id = u.userlid inner join \r\n"
				+ "authorities a on a.id = u.authoritylid where e.id = ? ";
		return template.queryForList(sql, id);
	}


	public List<Map<String, Object>> roleauthorize(long id) {
		String sql = "select * from employees where id = ?";
		return template.queryForList(sql, id);
	}
	
}
