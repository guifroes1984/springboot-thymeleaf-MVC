package br.com.guifroes.mvc.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.guifroes.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.guifroes.mvc.mudi.model.Pedido;
import br.com.guifroes.mvc.mudi.model.User;
import br.com.guifroes.mvc.mudi.repository.PedidoRepositoy;
import br.com.guifroes.mvc.mudi.repository.UserRepository;

@Controller
@RequestMapping("pedido")
public class PedidoController {
	
	@Autowired
	private PedidoRepositoy pedidoRepositoy;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("formulario")
	public String formulario(RequisicaoNovoPedido requisicao) {
		return "pedido/formulario";
	}
	
	@PostMapping("novo")
	public String novo(@Valid RequisicaoNovoPedido requisicao, BindingResult result) {
		if (result.hasErrors()) {
			return "pedido/formulario";
		}
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username);
		
		Pedido pedido = requisicao.toPedido();
		pedido.setUser(user);
		pedidoRepositoy.save(pedido);
		return "redirect:/home";
	}

}
