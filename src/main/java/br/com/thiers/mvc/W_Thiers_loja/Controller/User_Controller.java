package br.com.thiers.mvc.W_Thiers_loja.Controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.thiers.mvc.W_Thiers_loja.Model.Authority;
import br.com.thiers.mvc.W_Thiers_loja.Model.Pedido_Model;
import br.com.thiers.mvc.W_Thiers_loja.Model.StatusPedido;
import br.com.thiers.mvc.W_Thiers_loja.Model.User_Model;
import br.com.thiers.mvc.W_Thiers_loja.Repository.Pedido_Repository;
import br.com.thiers.mvc.W_Thiers_loja.Repository.User_Repository;

@Controller
@RequestMapping("/usuario")
public class User_Controller {

//	private Authority authority;

	
//	private BCryptPasswordEncoder bcryptEncoder;

	@Autowired
	private User_Repository userRepository;

	@Autowired
	private Pedido_Repository pedidoRepository;

	@GetMapping("pedido")
	public String home(Model model, Principal principal) {
		List<Pedido_Model> pedidos = pedidoRepository.findAllByUsuario(principal.getName());
		model.addAttribute("pedidos", pedidos);
		return "usuario/home";
	}

	@GetMapping("pedido/{status}")
	public String porStatus(@PathVariable("status") String status, Model model, Principal principal) {
		List<Pedido_Model> pedidos = pedidoRepository.findByStatusEUsuario(StatusPedido.valueOf(status.toUpperCase()),
				principal.getName());
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("status", status);
		return "usuario/home";
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/usuario/home";
	}

	@GetMapping("/cadastro")
	public String cadastro(Model model, Principal principal) {

		return "/usuario/cadastro";
	}

	@PostMapping("/cadastro")
	public String cadastro(@Valid User_Model user, BindingResult result) {
		if (result.hasErrors()) {
			return "cadastro";
		}
		
		if(user.getUsername() == user.getUsername()) {
			return "redirect:/home";
		}
		
		BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
//		bcryptEncoder = new BCryptPasswordEncoder();
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		user.setEnabled(true);
		user.getAuthorities().add(new Authority("ok"));
		userRepository.save(user);

		return "/login";
		
		
	}
	
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	
	@GetMapping(value = "/{id}")
	public User_Model findById(@PathVariable Long id) {
		User_Model result = userRepository.findById(id).get();
		return result;
	}

}