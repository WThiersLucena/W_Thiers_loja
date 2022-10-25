package br.com.thiers.mvc.W_Thiers_loja.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.thiers.mvc.W_Thiers_loja.Model.Pedido_Model;
import br.com.thiers.mvc.W_Thiers_loja.Model.StatusPedido;
import br.com.thiers.mvc.W_Thiers_loja.Repository.Pedido_Repository;


@Controller
@RequestMapping("/home")
public class Home_Controller {
	
	
	@Autowired
	private Pedido_Repository pedido_Repository;
	
	@GetMapping
	public String home(Model model, Principal principal) {
		Sort sort = Sort.by("dataDaEntrega").descending();
		PageRequest paginacao = PageRequest.of(0, 10, sort);
		
		List<Pedido_Model> pedidos = pedido_Repository.findByStatus(StatusPedido.ENTREGUE, paginacao);
		model.addAttribute("pedidos", pedidos);
		return "home";
	}
	
}
