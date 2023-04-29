package br.com.guifroes.mvc.mudi.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.guifroes.mvc.mudi.model.Pedido;

@Repository
public class PedidoRepositoy {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Pedido> recuperaTodosOsPedidos() {
		Query query = entityManager.createQuery("select p from Pedido p", Pedido.class);
		return query.getResultList();
		
	}

}
