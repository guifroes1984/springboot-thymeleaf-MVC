package br.com.guifroes.mvc.mudi.api;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.guifroes.mvc.mudi.dto.RequisicaoNovaOferta;
import br.com.guifroes.mvc.mudi.model.Oferta;
import br.com.guifroes.mvc.mudi.model.Pedido;
import br.com.guifroes.mvc.mudi.repository.PedidoRepositoy;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasRest {
	
	@Autowired
	private PedidoRepositoy pedidoRepositoy;
	
	@PostMapping
	public Oferta criaOferta(@Valid @RequestBody RequisicaoNovaOferta requisicao) {
		Optional<Pedido> pedidoBuscado = pedidoRepositoy.findById(requisicao.getPedidoId());
		if (!pedidoBuscado.isPresent()) {
			return null;
		}
		
		Pedido pedido = pedidoBuscado.get();
		
		Oferta nova = requisicao.toOferta();
		nova.setPedido(pedido);
		pedido.getOfertas().add(nova);
		pedidoRepositoy.save(pedido);
		
		return nova;
	}

}
