package springs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class User2 {
	
	@GetMapping("/success")
	public String success() {
		return "success";
	}

}
