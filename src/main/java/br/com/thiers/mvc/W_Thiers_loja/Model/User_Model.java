package br.com.thiers.mvc.W_Thiers_loja.Model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="users")
public class User_Model {
	
	// ANOTAÇÕES  - INICIO 
	// SEU PACKAGE MODEL E RESPONSAVEL PELA "MODELO OU MODELAGEM" DO SEU BANCO DE DADOS
	// AQUI VOCE DEVE CRIAR A TABELA E SUAS ENTIDADES "COLUNAS"
	// SEUS GETS E SETERS 
	
	// NA LINHA [ 7 ] 
	//  A anotação @Entity é utilizada para informar que uma classe também é uma entidade.
	//  A partir disso, a JPA estabelecerá a ligação entre a entidade e uma tabela de mesmo
	//  nome no banco de dados, onde os dados de objetos desse tipo poderão ser persistidos.


	// NA LINHA [ 8 ]
	// A anotação @Table(name="tb_user") e utilizada para informar e vincular o nome da classe 
	// ao nome da tabela 

	// ANOTAÇÕES  - FIM


	@Id
	private String username;
	
	private String password;
	private Boolean enabled;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
	private List<Pedido_Model> pedidos;
	
	public User_Model() {
	
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	
		

	@ElementCollection
	@CollectionTable(name = "authorities", joinColumns = @JoinColumn(name = "username"))
	private Set<Authority> authorities = new HashSet<>();

	public List<Pedido_Model> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido_Model> pedidos) {
		this.pedidos = pedidos;
	}
	public Set<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}













}
