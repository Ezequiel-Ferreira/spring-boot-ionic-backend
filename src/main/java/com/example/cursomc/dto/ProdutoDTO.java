package com.example.cursomc.dto;

import com.example.cursomc.domain.Produto;

public class ProdutoDTO {
	private Integer id;
	private String nome;
	private double preco;
	public ProdutoDTO(Integer id, String nome, double preco) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.id = id;
	}
	public ProdutoDTO(Produto obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.preco = obj.getPreco();
	}
	public ProdutoDTO() {
		super();
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	

}
