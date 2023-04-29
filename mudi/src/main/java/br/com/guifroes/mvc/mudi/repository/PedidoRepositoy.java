package br.com.guifroes.mvc.mudi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.guifroes.mvc.mudi.model.Pedido;

@Repository
public interface PedidoRepositoy extends JpaRepository<Pedido, Long> {
	
	}
