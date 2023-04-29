package br.com.guifroes.mvc.mudi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.guifroes.mvc.mudi.model.Pedido;
import br.com.guifroes.mvc.mudi.repository.PedidoRepositoy;

@Controller
public class HomeController {
	
	@Autowired
	private PedidoRepositoy repositoy;

	@GetMapping("/home")
	public String home(Model model) {
		List<Pedido> pedidos = repositoy.recuperaTodosOsPedidos();
		model.addAttribute("pedidos", pedidos);

		return "home";
	}

}
