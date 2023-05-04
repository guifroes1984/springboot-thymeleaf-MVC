package br.com.guifroes.mvc.mudi.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.guifroes.mvc.mudi.model.Pedido;
import br.com.guifroes.mvc.mudi.model.StatusPedido;
import br.com.guifroes.mvc.mudi.repository.PedidoRepositoy;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosRest {
	
	@Autowired
	private PedidoRepositoy pedidoRepositoy;
	
	@GetMapping("aguardando")
	public List<Pedido> getPedidosAguardandoOfertas() {
		Sort sort = Sort.by("id").descending();
		PageRequest paginacao = PageRequest.of(0, 10, sort);
		
		return pedidoRepositoy.findByStatus(StatusPedido.AGUARDANDO, paginacao);
	}

}