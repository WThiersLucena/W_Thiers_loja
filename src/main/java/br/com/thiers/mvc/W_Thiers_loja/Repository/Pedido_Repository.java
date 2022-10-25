package br.com.thiers.mvc.W_Thiers_loja.Repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.thiers.mvc.W_Thiers_loja.Model.Pedido_Model;
import br.com.thiers.mvc.W_Thiers_loja.Model.StatusPedido;

@Repository
public interface Pedido_Repository extends JpaRepository<Pedido_Model, Long> {

	@Cacheable("books")
	List<Pedido_Model> findByStatus(StatusPedido status, Pageable sort);

	@Query("select p from Pedido_Model p join p.user u where u.username = :username")
	List<Pedido_Model> findAllByUsuario(@Param("username")String username);

	@Query("select p from Pedido_Model p join p.user u where u.username = :username and p.status = :status")
	List<Pedido_Model> findByStatusEUsuario(@Param("status")StatusPedido status, @Param("username")String username);

}
