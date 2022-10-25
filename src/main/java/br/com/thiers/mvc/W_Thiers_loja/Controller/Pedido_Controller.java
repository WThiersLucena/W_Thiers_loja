package br.com.thiers.mvc.W_Thiers_loja.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.thiers.mvc.W_Thiers_loja.Dto.RequisicaoNovoPedido;
import br.com.thiers.mvc.W_Thiers_loja.Model.Pedido_Model;
import br.com.thiers.mvc.W_Thiers_loja.Model.User_Model;
import br.com.thiers.mvc.W_Thiers_loja.Repository.Pedido_Repository;
import br.com.thiers.mvc.W_Thiers_loja.Repository.User_Repository;


@Controller
@RequestMapping("pedido")
public class Pedido_Controller {

	@Autowired
	private Pedido_Repository pedidoRepository;
	
	@Autowired
	private User_Repository userRepository;

	@GetMapping("formulario")
	public String formulario(RequisicaoNovoPedido requisicao) {
		return "pedido/formulario";
	}
	
	@PostMapping("novo")
	public String novo(@Valid RequisicaoNovoPedido requisicao, BindingResult result) {
		if(result.hasErrors()) {
			return "pedido/formulario";
		}
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User_Model usuario = userRepository.findByUsername(username);
		Pedido_Model pedido = requisicao.toPedido();
		pedido.setUser(usuario);
		pedidoRepository.save(pedido);
		return "redirect:/home";
	}
}


