package br.com.guifroes.mvc.mudi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.guifroes.mvc.mudi.model.Pedido;
import br.com.guifroes.mvc.mudi.model.StatusPedido;
import br.com.guifroes.mvc.mudi.repository.PedidoRepositoy;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private PedidoRepositoy pedidoRepositoy;

	@GetMapping
	public String home(Model model, Principal principal) {
		Sort sort = Sort.by("dataEntrega").descending();
		PageRequest paginacao = PageRequest.of(0, 10, sort);
		
		List<Pedido> pedidos = pedidoRepositoy.findByStatus(StatusPedido.ENTREGUE, paginacao);
		model.addAttribute("pedidos", pedidos);
		return "home";
	}
	
}
