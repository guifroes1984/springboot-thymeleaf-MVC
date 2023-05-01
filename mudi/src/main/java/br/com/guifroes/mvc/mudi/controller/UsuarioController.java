package br.com.guifroes.mvc.mudi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.guifroes.mvc.mudi.model.Pedido;
import br.com.guifroes.mvc.mudi.model.StatusPedido;
import br.com.guifroes.mvc.mudi.repository.PedidoRepositoy;

@Controller
@RequestMapping("usuario")
public class UsuarioController {
	
	@Autowired
	private PedidoRepositoy pedidoRepositoy;

	@GetMapping("pedido")
	public String home(Model model, Principal principal) {
		List<Pedido> pedidos = pedidoRepositoy.findAllByUsuario(principal.getName());
		model.addAttribute("pedidos", pedidos);
		return "usuario/home";
	}
	
	@GetMapping("pedido/{status}")
	public String porStatus(@PathVariable("status") String status, Model model, Principal principal) {
		List<Pedido> pedidos = pedidoRepositoy.findByEStatus(StatusPedido.valueOf(status.toUpperCase()), principal.getName());
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("status", status);
		return "usuario/home";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/usuario/home";
	}

}
