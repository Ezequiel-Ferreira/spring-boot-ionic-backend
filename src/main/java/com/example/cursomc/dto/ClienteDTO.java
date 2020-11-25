package com.example.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.example.cursomc.domain.Cliente;


public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Não pode ser vazio")
	@Length(min = 3, max = 80, message = "O nome deve ter entre 3 e 80 caracteres")
	private String nome;
	@NotEmpty(message = "Não pode ser vazio")
	@Length(min = 7, max = 30, message = "O email deve ter entre 7 e 30 caracteres")
	private String email;
	private Integer tipo;
	
	public ClienteDTO(Integer id, String nome, String email, Integer tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.tipo = tipo;
	}
	public ClienteDTO() {
		super();
	}
	
	public ClienteDTO(Cliente cliente) {
		super();
		
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.tipo = cliente.getTipo().getCod();
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
	
	
}
