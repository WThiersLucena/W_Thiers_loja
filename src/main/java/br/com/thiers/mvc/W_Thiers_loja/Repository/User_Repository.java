package br.com.thiers.mvc.W_Thiers_loja.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.thiers.mvc.W_Thiers_loja.Model.User_Model;


@Repository
public interface User_Repository extends JpaRepository<User_Model, String> {

	User_Model findByUsername(String username);

	Optional<User_Model> findById(Long id);
	
	
	
	
	// A interface é um recurso muito utilizado em Java, bem como na maioria das linguagens orientadas a objeto,
	//  para “obrigar” a um determinado grupo de classes a ter métodos ou propriedades em comum para existir em
	//  um determinado contexto, contudo os métodos podem ser implementados em cada classe de uma maneira diferente.







}
