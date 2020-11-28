package com.example.cursomc.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.example.cursomc.resources.validation.ClienteInsert;

@ClienteInsert
public class ClienteDTONew {
	@NotEmpty(message = "O campo nome não pode ser vazio")
	@Length(min = 3, max = 40, message ="Deve ter entre 3 e 40 caracteres!")
	private String nome;
	@NotEmpty(message = "O campo email não pode ser vazio")
	@Email(message = "Email invalido!")
	private String email;
	@NotEmpty(message = "O campo Cpf ou Cnpj não pode ser vazio")
	private String cpfOuCnpj;
	
	private Integer tipo;
	@NotEmpty(message = "Deve ter pelo menos um telefone")
	@Length(min = 8, max = 12, message ="Deve conter ddd e o numero apenas!")
	private String telefone1;
	
	private String telefone2;
	private String telefone3;
	 	 
	private String lagradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private Integer cidadeId;



	public ClienteDTONew(String nome, String email, String cpfOuCnpj, Integer tipo, String telefone1, String telefone2,
			String telefone3, String lagradouro, String numero, String complemento, String bairro, String cep,
			Integer cidadeId) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.telefone3 = telefone3;
		this.lagradouro = lagradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidadeId = cidadeId;
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getLagradouro() {
		return lagradouro;
	}

	public void setLagradouro(String lagradouro) {
		this.lagradouro = lagradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}
	
	
	
	

}
