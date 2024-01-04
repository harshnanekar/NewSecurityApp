package springs.controller;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/User")
public class User {

	@Order(1)
	@GetMapping("/loginPage")
	public String login() {
		return "login";
	}
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/info")
	public String info() {
		return "info";
	}
	
	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	
}
