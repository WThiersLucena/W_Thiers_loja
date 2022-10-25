package br.com.thiers.mvc.W_Thiers_loja.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class Login_Controller {

	
	@GetMapping
	public String login() {
		return "/login";
	}
	
}
